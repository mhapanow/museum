package com.ciessa.museum.bz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.springframework.util.StringUtils;

import com.ciessa.museum.dao.GenericDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.ModelKey;
import com.ciessa.museum.model.User;
import com.ciessa.museum.tools.CollectionFactory;
import com.ciessa.museum.tools.Range;

/**
 *
 */
public abstract class CrudBzService<T extends ModelKey>
extends RestBaseServerResource {

	protected static final Logger log = Logger.getLogger(CrudBzService.class.getName());
	
	protected GenericDAO<T> myDao;
	protected Class<T> myClazz;
	
	public abstract void config();
	public abstract void setKey(T obj, JSONObject seed) throws ASException;
	
	/**
	 * Method called before a persist operation.
	 * 
	 * @param obj
	 *            The object to create / update
	 * @param seed
	 *            The original json object from which it came from
	 * @throws ASException
	 */
	public void prePersist(T obj, JSONObject seed) throws ASException {
		// Only to override
	}

	/**
	 * Method called after an object was changed
	 * 
	 * @param obj
	 *            The changed object
	 * @throws ASException
	 */
	public void postChange(T obj) throws ASException {
		// Only to override
	}

	/**
	 * Method called after an object was added
	 * 
	 * @param obj
	 *            The added object
	 * @throws ASException
	 */
	public void postAdd(T obj) throws ASException {
		// Only to override
	}

	/**
	 * Method called after an object was deleted
	 * 
	 * @param obj
	 *            The deleted object
	 * @throws ASException
	 */
	public void postDelete(T obj) throws ASException {
		// Only to override
	}
	
	/**
	 * Method called before a change operation
	 * 
	 * @param obj
	 *            The object to create / update
	 * @param json
	 *            The original json object from which it came from
	 * @throws ASException
	 */
	public void preModify(T obj, JSONObject json ) throws ASException {
		// Only to override
	}
	
	/**
	 * Get Delete mandatory fields
	 * 
	 * @return A list with all the fields needed for a delete request
	 */
	public String[] getMandatoryDeleteFields() {
		return new String[] {
				"identifier"
		};
	}

	/**
	 * Get Add mandatory fields
	 * 
	 * @return A list with all the fields needed for an add request
	 */
	public String[] getMandatoryAddFields() {
		return new String[] {
		};
	}

	/**
	 * Get Update mandatory fields
	 * 
	 * @return A list with all the fields needed for an update request
	 */
	public String[] getMandatoryUpdateFields() {
		return new String[] {
				"identifier",
		};
	}

	/**
	 * Get List fields
	 * 
	 * @return A list with all the fields needed for a list request
	 */
	public String[] getListFields() {
		return new String[] {
				"identifier",
		};
	}

	/**
	 * Get retrieve fields
	 * 
	 * @return A list with all the fields needed for a get request. null means all fields
	 */
	public String[] getRetrieveFields() {
		return null;
	}
	
	/**
	 * @return the myDao
	 */
	public GenericDAO<T> getMyDao() {
		return myDao;
	}

	/**
	 * @param myDao the myDao to set
	 */
	public void setMyDao(GenericDAO<T> myDao) {
		this.myDao = myDao;
	}
	
	/**
	 * @return the myClazz
	 */
	public Class<T> getMyClazz() {
		return myClazz;
	}
	
	/**
	 * @param myClazz the myClazz to set
	 */
	public void setMyClazz(Class<T> myClazz) {
		this.myClazz = myClazz;
	}

	/**
	 * Obtains information about a user
	 * 
	 * @return A JSON representation of the selected fields for a user
	 */
	@Get
	public String selectRetrieveOrList() {
		if( myDao == null ) config();
		JSONObject returnValue = new JSONObject();
		try {
			// obtain the id and validates the auth token
			if( obtainIdentifier(GENERAL_IDENTIFIER_KEY) == null ) {
				return list();
			} else {
				return retrieve();
			}
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
		}
		return returnValue.toString();
	}

	public String list() {
    	long start = markStart();
		JSONObject returnValue = null;
		try {
			List<T> list = new ArrayList<T>();
			
			// validate authToken
			this.getUserFromToken();

			// get range, if not defined use default value
			Range range = this.obtainRange();
			
			// Get Status query
			String status = this.obtainStringValue(STATUS, null);
	
			// Get order
			String order = this.obtainStringValue(ORDER, null);

			// Get the output fields
			String[] fields = this.obtainOutputFields(myClazz, getListFields());

			Map<String,String> attributes = CollectionFactory.createMap();
			
			// Staus filter
			List<Integer> statusList = null;
			if( StringUtils.hasText(status)) {
				if( status.contains(",")) {
					String tmpStatus[] = status.split(",");
					statusList = CollectionFactory.createList();
					for( String t : tmpStatus ) 
						statusList.add(Integer.parseInt(t));
				} else {
					statusList = Arrays.asList(new Integer[] {Integer.parseInt(status)});
				}
			}

			// retrieve all elements
			long millisPre = new Date().getTime();
			list = myDao.getUsingStatusAndRange(statusList, range, order, attributes);
			
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
	
	public String retrieve()
	{
		long start = markStart();
		JSONObject returnValue;
		try {
			// obtain the id and validates the auth token
			getUserFromToken();
			String identifier = obtainIdentifier(GENERAL_IDENTIFIER_KEY);
			final T obj = myDao.get(identifier);
			
			// Get the output fields
			String[] fields = this.obtainOutputFields(myClazz, getRetrieveFields());

			// Obtains the user JSON representation
			returnValue = getJSONRepresentationFromObject(obj, fields);

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

	@Put("json")
	public String add(final JsonRepresentation entity) {
		if( myDao == null ) config();
		long start = markStart();
		try {
			// validate authToken
			final User user = getUserFromToken();
			final JSONObject obj = entity.getJsonObject();

			// check mandatory fields
			log.info("check mandatory fields");
			checkMandatoryFields(obj, getMandatoryAddFields());

			// check for permissions
			if(!hasPermission(user, obj, myClazz, OPERATION_WRITE))
				throw ASExceptionHelper.forbiddenException();
			
			log.info("setting attributes");
			final T newObj = myClazz.newInstance();

			preModify(newObj, obj);
			setPropertiesFromJSONObject(obj, newObj, EMPTY_SET);
			setKey(newObj, obj);
			prePersist(newObj, obj);
			
			myDao.create(newObj);
			log.info("object created: " + newObj.getIdentifier());
			postAdd(newObj);
			
			return getJSONRepresentationFromObject(newObj, obtainOutputFields(myClazz)).toString();

		} catch (JSONException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e)).toString();
		} catch (Exception e) {
			if( e instanceof ASException && ((ASException)e).getErrorCode() == ASExceptionHelper.AS_EXCEPTION_ALREADYEXISTS_CODE)
				return getJSONRepresentationFromException(ASExceptionHelper.alreadyExistsException()).toString();
			
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(e).toString();
		} finally {
			markEnd(start);
		}

	}

	@Post("json")
	public String change(JsonRepresentation entity) {
		if( myDao == null ) config();
		long start = markStart();
		try {

			// validate authToken
			getUserFromToken();
			final JSONObject obj = entity.getJsonObject();

			// check mandatory fields
			log.info("check mandatory fields");
			checkMandatoryFields(obj, getMandatoryUpdateFields());

			final String identifier = obtainLowerCaseIdentifierFromJSON(obj);

			T modObj = myDao.get(identifier);
			preModify(modObj, obj);
			setPropertiesFromJSONObject(obj, modObj, EMPTY_SET);
			prePersist(modObj, obj);

			myDao.update(modObj);
			log.info("object updated");
			postChange(modObj);

			return getJSONRepresentationFromObject(modObj, obtainOutputFields(myClazz)).toString();
			
		} catch (JSONException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(ASExceptionHelper.defaultException(e.getMessage(), e)).toString();
		} catch (Exception e) {
			if( e instanceof ASException && ((ASException)e).getErrorCode() == ASExceptionHelper.AS_EXCEPTION_NOTFOUND_CODE)
				return getJSONRepresentationFromException(ASExceptionHelper.notFoundException()).toString();
			
			log.log(Level.SEVERE, e.getMessage(), e);
			return getJSONRepresentationFromException(e).toString();
		} finally {
			markEnd(start);
		}

	}

	@Delete("json")
	public String delete(JsonRepresentation entity) {
		if( myDao == null ) config();
		long start = markStart();
		try {

			// validate authToken
			final User user = getUserFromToken();
			String identifier;
			
			if( null != entity ) {
				final JSONObject obj = entity.getJsonObject();

				//check mandatory fields
				log.info("check mandatory fields");
				checkMandatoryFields(obj, getMandatoryDeleteFields());

				// checks if the authToken's user has perms to touch the identifier object
				identifier = obtainLowerCaseIdentifierFromJSON(obj);
				if(!hasPermission(user, obj, myClazz, OPERATION_WRITE)) {
					throw ASExceptionHelper.forbiddenException();
				}
			} else {
				identifier = obtainIdentifier(GENERAL_IDENTIFIER_KEY);
			}
			
			T delObj = myDao.get(identifier);
			myDao.delete(identifier);
			log.info("object deleted: " + delObj.getIdentifier());
			postDelete(delObj);

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
}
