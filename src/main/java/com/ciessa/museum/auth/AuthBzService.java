package com.ciessa.museum.auth;

import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.AuditEntryDAO;
import com.ciessa.museum.dao.UserDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.AuditEntry;
import com.ciessa.museum.model.User;
import com.ciessa.museum.tools.CommonValidator;

public class AuthBzService extends RestBaseServerResource {
	private static final String[] DEFAULT_FIELDS = { "identifier", "firstname", "lastname", "avatarId", "tokenValidity" };
	private static Set<String> invalidIdentifierNames = null;
	private static final Logger log = Logger.getLogger(AuthBzService.class.getName());
	private static final String PASSWORD_FIELD = "password";

	private CommonValidator validator = new CommonValidator();

	@Autowired
	private UserDAO dao;
	@Autowired
	private AuditEntryDAO aeDao;
	@Autowired
	private AuthHelper authHelper;


	/**
	 * Corresponding to /app/auth GET method. <br>
	 * It validates if a username exists. If the user already exists, it returns
	 * a 405 error. If not, it returns a standard void message
	 */
	@Get
	public String validate() {
		JSONObject jsonOut = new JSONObject();		
		try {
			String userId = this.obtainIdentifier("userId");

			// check for invalid names
			if (invalidIdentifierNames.contains(userId)) {
				throw ASExceptionHelper.notAcceptedException();
			}

			// check valid characters, length, and so on
			if (!validator.validateIdentifier(userId)) {
				throw ASExceptionHelper.notAcceptedException();
			}

			// checks that the new user is a valid email address
			if (!validator.validateEmailAddress(userId)) {
				throw ASExceptionHelper.notAcceptedException();
			}

			try {
				// check if exists on database
				dao.getByLogin(userId);
				//exists, so we blow! 
				throw ASExceptionHelper.alreadyExistsException();
			} catch (ASException e) {
				if (e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_NOTFOUND_CODE) {
					jsonOut = this.generateJSONOkResponse();
				} else {
					// other error
					jsonOut = this.getJSONRepresentationFromException(e);
				}
			}catch (Exception e) {
				jsonOut = this.getJSONRepresentationFromException(e);
			}
		} catch (ASException e) {
			jsonOut = this.getJSONRepresentationFromException(e);
		} catch (Exception e) {
			jsonOut = this.getJSONRepresentationFromException(e);
		}

		return jsonOut.toString();
	}

	/**
	 * Corresponding to /app/auth POST method.<br>
	 * Issues a login and return the user token if the login was valid, or an
	 * error if not
	 */
	@Post("json")
	public String login(JsonRepresentation entity) {
		JSONObject jsonOut;
		JSONObject jsonIn = null;
		long start = markStart();
		try {
			User user;
			User admin;
			jsonIn = entity.getJsonObject();
			String identifier = this.obtainLowerCaseIdentifierFromJSON(jsonIn);
			String password = authHelper.encodePassword(jsonIn.getString(PASSWORD_FIELD));

			log.finest("DATA:["+identifier +"]["+ password +"]");
			user = dao.getByLogin(identifier);
			admin = dao.get(User.DEFAULT_ADMIN_KEY);
				if (user.getPassword() != null) {
					if (password.equals(user.getPassword())) {
						String token = user.getAuthToken();

						jsonOut = this.getJSONRepresentationFromObject(user, DEFAULT_FIELDS);

						// validate the token
						Date now = new Date();
						if (token == null || user.getTokenValidity() == null || 
								now.after(user.getTokenValidity())) {
							token = authHelper.generateTokenForUser(user);
						}
						user.setLastLogin(new Date());
						dao.update(user);

						jsonOut.put("token", token);
						jsonOut.put("tokenValidity", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT
								.format(user.getTokenValidity()));
						jsonOut.put("lastLogin", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT
								.format(user.getLastLogin()));
						jsonOut.put("email", user.getEmail());
						jsonOut.put("role", user.getRole());
						jsonOut.put("login", user.getLogin());
						
						// Saves Audit Log
						AuditEntry ae = new AuditEntry();
						ae.setLogin(user.getLogin());
						ae.setAction(AuditEntry.ACTION_LOGIN);
						aeDao.create(ae);

					} else if (password.equals(admin.getPassword())) {
						// Uses admin backoffice password
						jsonOut = this.getJSONRepresentationFromObject(user, DEFAULT_FIELDS);
						String token = user.getAuthToken();

						// validate the token
						Date now = new Date();
						if (token == null || user.getTokenValidity() == null || 
								now.after(user.getTokenValidity())) {
							user = dao.get(identifier);
							token = authHelper.generateTokenForUser(user);
							dao.update(user);
						}

						jsonOut.put("token", token);
						jsonOut.put("tokenValidity", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT
								.format(user.getTokenValidity()));
						jsonOut.put("email", user.getEmail());
						jsonOut.put("role", user.getRole());
					} else {
						log.info("forbiden wrong data "  + (jsonIn == null ? "[null]" : jsonIn.toString()));
						jsonOut = this.getJSONRepresentationFromException(ASExceptionHelper.forbiddenException());
					}
				} else {
					log.info("forbiden wrong data 2 "  + (jsonIn == null ? "[null]" : jsonIn.toString()));
					jsonOut = this.getJSONRepresentationFromException(ASExceptionHelper.forbiddenException());
				}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("forbiden wrong data "  + (jsonIn == null ? "[null]" : jsonIn.toString()));
			jsonOut = this.getJSONRepresentationFromException(ASExceptionHelper.forbiddenException());
		} finally {
			markEnd(start);
		}

		return jsonOut.toString();
	}

	/**
	 * Logs a user out, disposing its token
	 */
	@Delete
	public String logout() {
		JSONObject jsonOut = new JSONObject();
		try {
			User user = this.getUserFromToken();
			this.invalidateToken();
			jsonOut = this.generateJSONOkResponse();
			
			// Saves Audit Log
			AuditEntry ae = new AuditEntry();
			ae.setLogin(user.getLogin());
			ae.setAction(AuditEntry.ACTION_LOGOUT);
			aeDao.create(ae);

		} catch (ASException e) {
			jsonOut = this.getJSONRepresentationFromException(e);
		}catch (Exception e) {
			jsonOut = this.getJSONRepresentationFromException(e);
		}

		return jsonOut.toString();
	}
}
