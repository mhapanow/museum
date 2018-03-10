package com.ciessa.museum.auth;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.springframework.beans.factory.annotation.Autowired;

import com.ciessa.museum.bz.RestBaseServerResource;
import com.ciessa.museum.dao.UserDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.User;

public class PassBzService extends RestBaseServerResource {
	private static final Logger log = Logger.getLogger(PassBzService.class.getName());
	private static final String[] DEFAULT_FIELDS = { "currentPassword","newPassword"};
	private static final String[] SET_FIELDS = { "identifier","newPassword"};
	private static final String CURRENT_PASSWORD = "currentPassword";
	private static final String NEW_PASSWORD = "newPassword";
	
	@Autowired
	UserDAO dao;
	@Autowired
	AuthHelper authHelper;

	@Post("json")
	public String change(JsonRepresentation entity) {
		JSONObject jsonOut = new JSONObject();
		try{
			User user = this.getUserFromToken();
			JSONObject obj = entity.getJsonObject();
			
			checkMandatoryFields(obj, DEFAULT_FIELDS);
			String currentPassword = authHelper.encodePassword(obj.getString(CURRENT_PASSWORD));
			String newPassword = authHelper.encodePassword(obj.getString(NEW_PASSWORD));
			if(!user.getPassword().equals(currentPassword)){
				throw ASExceptionHelper.invalidArgumentsException();
			}
			user.setPassword(newPassword);
			dao.update(user);
			jsonOut = this.generateJSONOkResponse();

		}catch(ASException e){
			log.log(Level.SEVERE, "exception catched", e);
			jsonOut = this.getJSONRepresentationFromException(e);
			
		}catch(Exception e){
			log.log(Level.SEVERE, "exception catched", e);
			jsonOut = this.getJSONRepresentationFromException(e);
		}
		return jsonOut.toString();
	}

    @Put("json")
	public String force(JsonRepresentation entity) {
		JSONObject jsonOut = new JSONObject();
		try{
			User user = this.getUserFromToken();
			JSONObject obj = entity.getJsonObject();

			if(user.getRole() != User.ROLE_ADMIN) {
				return this.getJSONRepresentationFromException(ASExceptionHelper.forbiddenException()).toString();
			}
			
			checkMandatoryFields(obj, SET_FIELDS);
			
			User target = dao.get(obj.getString("identifier"));
			if(target == null) {
				return this.getJSONRepresentationFromException(ASExceptionHelper.notFoundException()).toString();
			}
			
			String newPassword = authHelper.encodePassword(obj.getString(NEW_PASSWORD));
			target.setPassword(newPassword);
			dao.update(target);
			jsonOut = this.generateJSONOkResponse();

		}catch(ASException e){
			log.log(Level.SEVERE, "exception catched", e);
			jsonOut = this.getJSONRepresentationFromException(e);
			
		}catch(Exception e){
			log.log(Level.SEVERE, "exception catched", e);
			jsonOut = this.getJSONRepresentationFromException(e);
		}
		return jsonOut.toString();
	}

}
