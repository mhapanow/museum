package com.ciessa.museum.bz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ciessa.museum.auth.AuthHelper;
import com.ciessa.museum.dao.UserDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.User;
import com.ciessa.museum.tools.CollectionFactory;
import com.ciessa.museum.tools.CommonValidator;
import com.ciessa.museum.tools.Range;

/**
*
*/
public class UserBzService
extends CrudBzService<User> {

	private static final Logger log = Logger.getLogger(UserBzService.class.getName());

	@Autowired
	private UserDAO dao;
	@Autowired
	private AuthHelper authHelper;
	@Autowired
	private CommonValidator commonValidator;

	private static final String[] MANDATORY_ADD_FIELDS = new String[] {
		"contactInfo.mail",
		"password"
	};
	
	private static final String[] MANDATORY_UPDATE_FIELDS = new String[] {
		"identifier",
		"contactInfo.mail"
	};
	
	private static final String[] MANDATORY_DELETE_FIELDS = new String[] {
		"identifier"
	};
	
	@Override
	public String[] getListFields() {
		return new String[] {
				"identifier",
				"avatarId",
				"firstname",
				"lastname",
				"fullname",
				"creationDateTime",
				"statusModificationDateTime",
				"lastUpdate",
				"viewLocation.country",
				"lastLogin",
				"securitySettings.status",
				"securitySettings.role",
				"activityStatus",
				"gender"
		};
	}

	@Override
	public String retrieve()
	{
		long start = markStart();
		JSONObject returnValue;
		try {
			// obtain the id and validates the auth token
			User user = getUserFromToken();
			final String userToViewId = (obtainIdentifier(GENERAL_IDENTIFIER_KEY) == null)
					? user.getKey() : obtainIdentifier("identifier");

					// Check User type
					if(!userToViewId.equals("me")) {
						if (user.getKey().equals(userToViewId) || userToViewId == null ) {
							user = dao.get(user.getKey());
						} else { // I want to see public info from a user 
							user = dao.get(userToViewId);
						}
					}

					// Get the output fields
					String[] fields = this.obtainOutputFields(User.class, null);

					// Obtains the user JSON representation
					returnValue = getJSONRepresentationFromObject(user, fields);

		} catch (ASException e) {
			if( e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE || 
					e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
				log.log(Level.INFO, e.getMessage());
			} else {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			returnValue = getJSONRepresentationFromException(e);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			returnValue = getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e));
		} finally {
			markEnd(start);
		}
		return returnValue.toString();
	}

	@Override
	public String add(final JsonRepresentation entity) {
		String response;
		long start = markStart();
		try {
			// validate authToken
			final User user = getUserFromToken();
			final JSONObject obj = entity.getJsonObject();

			//check mandatory fields
			log.info("check mandatory fields");
			checkMandatoryFields(obj, MANDATORY_ADD_FIELDS);

			if(user.getRole() != User.ROLE_ADMIN)
				throw ASExceptionHelper.forbiddenException();

			//checks that the user email is not existent to avoid duplicates
			User altUser = dao.getByEmail(obj.getString("mail"));
			if( altUser != null ) throw ASExceptionHelper.alreadyExistsException();

			log.info("setting user attributes");
			final User newUser = new User();

			setPropertiesFromJSONObject(obj, newUser, EMPTY_SET);

			newUser.setPassword(authHelper.encodePassword(obj.getString("password")));

			// validates user data
			if (!validateFields(newUser)) {
				throw ASExceptionHelper.invalidArgumentsException(INVALID_DEFAULT_FIELDS);
			}

			dao.create(newUser);
			log.info("user created: " + newUser.getFullname());

			response = generateJSONOkResponse().toString();

		} catch (JSONException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e)).toString();
		} catch (ASException e) {
			if( e instanceof ASException && ((ASException)e).getErrorCode() == ASExceptionHelper.AS_EXCEPTION_ALREADYEXISTS_CODE)
				return getJSONRepresentationFromException(ASExceptionHelper.alreadyExistsException()).toString();

			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(e).toString();
		} finally {
			markEnd(start);
		}

		return response;
	}

	@Override
	public String change(JsonRepresentation entity) {

		long start = markStart();
		try {

			// validate authToken
			final User user = getUserFromToken();
			final JSONObject obj = entity.getJsonObject();

			//check mandatory fields
			log.info("check mandatory fields");
			checkMandatoryFields(obj, MANDATORY_UPDATE_FIELDS);

			// checks if the authToken's user has perms to touch the identifier object
			final String identifier = obtainLowerCaseIdentifierFromJSON(obj);
			if (!StringUtils.hasText(identifier) || 
					(user.getRole() != User.ROLE_ADMIN && !user.getIdentifier().equalsIgnoreCase(identifier))) {
				throw ASExceptionHelper.forbiddenException();
			}

			User modUser = dao.get(identifier);
			setPropertiesFromJSONObject(obj, modUser, EMPTY_SET);

			// validates user data
			if (!validateFields(modUser)) {
				throw ASExceptionHelper.invalidArgumentsException(INVALID_DEFAULT_FIELDS);
			}

			dao.update(modUser);
			log.info("user updated");

		} catch (JSONException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e)).toString();
		} catch (ASException e) {
			if( e instanceof ASException && ((ASException)e).getErrorCode() == ASExceptionHelper.AS_EXCEPTION_NOTFOUND_CODE)
				return getJSONRepresentationFromException(ASExceptionHelper.notFoundException()).toString();

			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(e).toString();
		} finally {
			markEnd(start);
		}

		return generateJSONOkResponse().toString();
	}

	public String list() {
   	long start = markStart();
		JSONObject returnValue = null;
		try {
			List<User> list = new ArrayList<User>();
			
			// validate authToken
			this.getUserFromToken();

			// get range, if not defined use default value
			Range range = this.obtainRange();
			
			// Get Status query
			String status = this.obtainStringValue(STATUS, null);
	
			// Get order
			String order = this.obtainStringValue(ORDER, null);

			// Get role
			String role = this.obtainStringValue(ROLE, null);

			// Get the output fields
			String[] fields = this.obtainOutputFields(myClazz, getListFields());

			Map<String,String> attributes = CollectionFactory.createMap();
			
			// Status filter
			List<Integer> statusList = null;
			if( StringUtils.hasText(status)) {
				statusList = CollectionFactory.createList();
				String parts[] = status.split(",");
				for(String part : parts ) {
					try {
						statusList.add(Integer.valueOf(part));
					} catch( Exception e) {}
				}
			}

			// Role filter
			List<Integer> roleList = null;
			if( StringUtils.hasText(role)) {
				roleList = CollectionFactory.createList();
				String parts[] = role.split(",");
				for(String part : parts ) {
					try {
						roleList.add(Integer.valueOf(part));
					} catch( Exception e) {}
				}
			}
			
			// retrieve all elements
			long millisPre = new Date().getTime();
			list = dao.getUsingStatusAndRange(statusList, range, order, attributes);
			
			long diff = new Date().getTime() - millisPre;
			
			// Logs the result
			log.info("Number of elements found [" + list.size() + "] in " + diff + " millis");
			returnValue = this.getJSONRepresentationFromArrayOfObjects(list, fields);
			
			if( attributes.containsKey("recordCount"))
				returnValue.put("recordCount", Long.valueOf(attributes.get("recordCount")));
			else 
				returnValue.put("recordCount", list.size());
				
   	} catch (ASException e) {
   		if( e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE || 
   				e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE) {
   			log.log(Level.INFO, e.getMessage());
   		} else {
   			log.log(Level.SEVERE, e.getMessage(), e);
   		}
   		returnValue = getJSONRepresentationFromException(e);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			returnValue = this.getJSONRepresentationFromException(e);
		} finally {
			markEnd(start);
		}
		return returnValue.toString();
	}

	private boolean validateFields(final User user) throws ASException {

		if (!commonValidator.validateIdentifier(user.getKey())) {
			INVALID_DEFAULT_FIELDS.add("userId");
		}

		final String lastName = user.getLastname();
		final String name = user.getFirstname();

		if( null == name )
			user.setFirstname("");

		if( null == lastName )
			user.setLastname("");

		final String fullname = user.getFullname();
		if(null == fullname) {
			user.setFirstname("");
			user.setLastname("");
		}

		if (user.getEmail() != null &&
				commonValidator.validateEmailAddress(user.getEmail())
				&& !validateUniqueMail(user)) {

			INVALID_DEFAULT_FIELDS.add("mail");
		}

		return INVALID_DEFAULT_FIELDS.isEmpty();
	}

	@Override
	public String delete(JsonRepresentation entity) {
		long start = markStart();
		try {

			// validate authToken
			final User user = getUserFromToken();
			final JSONObject obj = entity.getJsonObject();

			//check mandatory fields
			log.info("check mandatory fields");
			checkMandatoryFields(obj, MANDATORY_DELETE_FIELDS);

			// checks if the authToken's user has perms to touch the identifier object
			final String identifier = obtainLowerCaseIdentifierFromJSON(obj);
			if (!StringUtils.hasText(identifier) || 
					(user.getRole() != User.ROLE_ADMIN && !user.getKey().equalsIgnoreCase(identifier))) {
				throw ASExceptionHelper.forbiddenException();
			}

			dao.delete(identifier);
			log.info("user deleted");

		} catch (JSONException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e)).toString();
		} catch (ASException e) {
			if( e instanceof ASException && ((ASException)e).getErrorCode() == ASExceptionHelper.AS_EXCEPTION_NOTFOUND_CODE)
				return getJSONRepresentationFromException(ASExceptionHelper.notFoundException()).toString();

			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(e).toString();
		} finally {
			markEnd(start);
		}

		return generateJSONOkResponse().toString();
	}


	@Override
	public void config() {
		setMyDao(dao);
		setMyClazz(User.class);
	}

	@Override
	public void setKey(User obj, JSONObject seed) throws ASException {
		// TODO Auto-generated method stub
		
	}

	public boolean validateUniqueMail(User user) throws ASException {
		
		User otherUser = dao.getByEmail(user.getEmail());
		if	(null == otherUser) {
			return true;
		}
		if	(user.getIdentifier().equals(otherUser.getIdentifier())) {
			return true;
		}
		
		return false;
	}

}
