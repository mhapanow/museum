package com.ciessa.museum.bz;

import java.lang.reflect.InvocationTargetException;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ciessa.museum.auth.AuthHelper;
import com.ciessa.museum.dao.UserDAO;
import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.exception.ASExceptionHelper;
import com.ciessa.museum.model.SystemConfiguration;
import com.ciessa.museum.model.User;
import com.ciessa.museum.tools.CollectionFactory;
import com.ciessa.museum.tools.Range;

public abstract class RestBaseServerResource extends ServerResource {

	protected static final Logger logger = Logger.getLogger(RestBaseServerResource.class.getName()); 

	protected static final int OPERATION_READ = 0;
	protected static final int OPERATION_WRITE = 1;
	
	protected List<String> INVALID_DEFAULT_FIELDS = CollectionFactory.createList();
	protected static final String[] EMPTY_STRING_ARRAY = new String[0];
	protected static final List<String> INVALID_READ_FIELDS = Arrays.asList(new String[] {"key"});
	protected static final List<String> INVALID_WRITE_FIELDS = Arrays.asList(new String[] {"key", "identifier", "creationDateTime", "lastUpdate", "password", "authToken"});
	
	protected static final String GENERAL_IDENTIFIER_KEY = "identifier";
	protected static final String USER_IDENTIFIER_KEY = "userId";
	protected static final String ADMIN_USER = "admin";
	protected static final String Q = "q";
	protected static final String COUNTRY = "country";
	protected static final String STATUS = "status";
	protected static final String ORDER = "order";
	protected static final String ENTITY_ID = "entityId";
	protected static final String ENTITY_KIND = "entityKind";
	protected static final String DATE = "date";
	protected static final String CHECKIN_TYPE = "checkinType";
	protected static final String ROLE = "role";
	protected static final String DEFAULT_LEVEL = "all";
	protected static final String FIELDS = "fields";
	protected static final Set<String> EMPTY_SET = new HashSet<String>();

	@Autowired
	private UserDAO myDao;
	@Autowired
	private AuthHelper authHelper;
	@Autowired
	private SystemConfiguration systemConfiguration;

	@Override
	public void init(Context arg0, Request arg1, Response arg2) {
		super.init(arg0, arg1, arg2);
	}
	
	/**
	 * Returns a generic OK response
	 * 
	 * @return A generic OK response in JSON Format
	 */
	public JSONObject generateJSONOkResponse() {
		JSONObject response = null;
		try {
			response = new JSONObject();
			response.put("status", 1);
			this.putSystemDateTime(response);
		} catch(JSONException e) {
			// Not even possible
		}
		return response;
	}

	/**
	 * Inserts System Date into a JSON Object to sequence it and to give it
	 * unicity
	 * 
	 * @param object
	 *            The JSON Object to modify
	 * @throws JSONException
	 */
	public void putSystemDateTime(JSONObject object) throws JSONException {
		object.put("systemDateTime", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date()));
	}
	
	/**
	 * Gets a map with all the request parameters
	 * 
	 * @return A map with the request parameters
	 */
	public Map<String,String> getParameters() {
		Map<String, String> ret = CollectionFactory.createMap();
		if( this.getRequest() == null ) return ret;
		Reference url = this.getRequest().getResourceRef();
		Form query = url.getQueryAsForm();
		ret.putAll(query.getValuesMap());
		return ret;
	}

	/**
	 * Gets a map with all the request parameters
	 * 
	 * @return A map with the request parameters
	 */
	public Map<String,String> getHeaders() {
		Map<String, String> ret = CollectionFactory.createMap();
		if( this.getRequest() == null ) return ret;
		ret.putAll(this.getRequest().getHeaders().getValuesMap());
		return ret;
	}

	/**
	 * Checks if a JSON request has all the fields needed
	 * 
	 * @param obj
	 *            The JSON request
	 * @param fields
	 *            The fields to check
	 * @throws ASException
	 *             Thrown if a field is missing
	 */
	public void checkMandatoryFields(JSONObject obj, String[] fields) throws ASException {
		for( String field : fields ) {
			String[] parts = field.split("\\.");
			if(!obj.has(parts[0])) {
				throw ASExceptionHelper.invalidArgumentsException(parts[0]);
			}
			if( parts.length > 1 ) {
				StringBuffer sb = new StringBuffer();
				for( int i = 1; i < parts.length; i++ ) {
					if( i != 1 ) sb.append(".");
					sb.append(parts[i]);
				}
				checkMandatoryFields(obj.getJSONObject(parts[0]), new String[] {sb.toString()});
			}
		}
	}
	
	/**
	 * Checks if a user has permission to an entity
	 * 
	 * @param user
	 *            The user to be checked
	 * @param obj
	 *            The object to be checked
	 * @param clazz
	 *            The object class, if the object is null (for example, creation
	 *            of a new entity)
	 * @param operation
	 *            OPERATION_WRITE, OPERATION_READ
	 * @return true if the user has permission for the object, false if not
	 */
	public boolean hasPermission(User user, Object obj, Class<?> clazz, int operation ) {
		return true;
	}
	
	/**
	 * Searches the identifier attribute on a JSON object and returns the lower
	 * case value if the attributes exists
	 * 
	 * @param object
	 * @return
	 */
	public String obtainLowerCaseIdentifierFromJSON(JSONObject object) {
		String identifier = null;
		try {
			identifier = object.getString(GENERAL_IDENTIFIER_KEY);
		} catch (JSONException e) {
		}
		if (identifier != null) {
			return identifier.toLowerCase();
		}
		return null;
	}
	
	/**
	 * Searches the key parameter on the URL's query. If found returns the value
	 * otherwise the defaultValue
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String obtainStringValue(String key, String defaultValue) {
		String identifierAttribute = (String)getRequest().getAttributes().get(key);
		if (identifierAttribute != null && !"".equals(identifierAttribute) && !"null".equals(identifierAttribute)) {
			return identifierAttribute;
		}

		Map<String,String> parameters = this.getParameters();
		String value = parameters.get(key);
		if (value == null || "null".equals(value)) {
			return defaultValue;
		}
		return value;
	}
	
	/**
	 * Searches the key parameter on the URL's query. If found returns the value
	 * otherwise the defaultValue
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Integer obtainIntegerValue(String key, Integer defaultValue) {
		String val = obtainStringValue(key, null);
		try {
			return StringUtils.hasText(val) ? Integer.valueOf(val) : defaultValue;
		} catch( NumberFormatException e ) {
			logger.finer("Cannot convert value " + val + " to integer!");
			return defaultValue;
		}
	}
	
	/**
	 * Searches the key parameter on the URL's query. If found returns the value
	 * otherwise the defaultValue
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Double obtainDoubleValue(String key, Double defaultValue) {
		String val = obtainStringValue(key, null);
		try {
			return StringUtils.hasText(val) ? Double.valueOf(val) : defaultValue;
		} catch( NumberFormatException e ) {
			logger.finer("Cannot convert value " + val + " to double!");
			return defaultValue;
		}
	}

	/**
	 * Useful to retrieve identifiers on URL's Query
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String obtainLowerCaseStringValue(String key, String defaultValue) {
		String value = this.obtainStringValue(key, defaultValue);
		if (value != null) {
			return value.toLowerCase();
		}
		return value;
	}
	

	/**
	 * Searches the key parameter on the URL's query. If found returns the value
	 * otherwise the defaultValue. The date must be in
	 * ISO_DATETIME_TIME_ZONE_FORMAT format.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Date obtainDateValue(String key, Date defaultValue) {
		Map<String,String> parameters = this.getParameters();
		String value = parameters.get(key);
		if (value == null) {
			return defaultValue;
		}

		String pattern = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern();
		Date date = null;
		try {
			date = DateUtils.parseDate((String)value, new String[] { pattern });
		} catch (ParseException e) {
		}
		if (date == null) {
			return defaultValue;
		}
		return date;
	}

	/**
	 * Searches the parameters TO and FROM on the URL's query. If found returns
	 * the value otherwise the system default values See:
	 * http://db.apache.org/jdo
	 * /api20/apidocs/javax/jdo/Query.html#setRange(long, long) fromIncl -
	 * 0-based inclusive start index toExcl - 0-based exclusive end index.
	 * 
	 * @return
	 */
	public Range obtainRange() {
		int defaultTo = this.systemConfiguration.getDefaultToRange();
		int defaultFrom = this.systemConfiguration.getDefaultFromRange();
		int from = this.obtainIntValue("from", defaultFrom);
		int to = this.obtainIntValue("to", from+defaultTo);
		
		if (from < 0) {
			from = defaultFrom;
		}
		if (to < from) {
			to = defaultTo;
			from = defaultFrom;
		}
		
		if( from == 0 && to == 0 ) {
			to = 999999;
		}
			
		return new Range(from, to);
	}
	
	/**
	 * Obtains the response fields according to the specific request and
	 * defaults
	 * 
	 * @param clazz
	 *            The class to search for fields
	 * @param defaultFields
	 *            A list of default fields
	 *            
	 * @return A String array of the fields that the service should respond
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String[] obtainOutputFields(Class<?> clazz, String[] defaultFields) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String fieldsReq = obtainStringValue(FIELDS, null);
		String ret[];
		if( StringUtils.hasText(fieldsReq)) {
			ret = fieldsReq.split(",");
		} else {
			if( defaultFields != null && defaultFields.length > 0 ) {
				ret = defaultFields;
			} else {
				ret = obtainOutputFields(clazz);
			}
		}
		
		return ret;
	}
	
	/**
	 * Searches the key parameter on the URL's query. If found returns the value
	 * otherwise the defaultValue
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Boolean obtainBooleanValue(String key, Boolean defaultValue) {
		Map<String,String> parameters = this.getParameters();
		String value = parameters.get(key);
		if (value == null) {
			return defaultValue;
		}
		Boolean booleanValue = defaultValue;
		if (value.compareToIgnoreCase("true") == 0) {
			return true;
		} else 	if (value.compareToIgnoreCase("false") == 0) {
			return false;
		}
		return booleanValue;
	}
	
	/**
	 * Searches the key parameter on the URL's query. The parameter can be a
	 * list of values (comma separated) or a single value If found returns the
	 * value/s on a collection otherwise the defaultValue
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public List<Integer> obtainIntValues(String key, List<Integer> defaultValue) {
		List<Integer> ret = CollectionFactory.createList();
		Map<String,String> parameters = this.getParameters();
		String value = parameters.get(key);
		if (value == null) {
			return defaultValue;
		}
		StringTokenizer st = new StringTokenizer(value, ",");
		while (st.hasMoreTokens()) { 
			String token = st.nextToken(); 
			try {
				int intValue = Integer.parseInt(token.trim());
				ret.add(intValue);
			} catch (NumberFormatException e) {
			}
		}
		return ret;
	}
	
	/**
	 * Searches the key parameter on the URL's query. If found returns the value
	 * otherwise the defaultValue
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int obtainIntValue(String key, int defaultValue) {
		Map<String,String> parameters = this.getParameters();
		String value = parameters.get(key);
		if (value == null) {
			return defaultValue;
		}
		int intValue = defaultValue;
		try {
		     intValue = Integer.parseInt(value);
		     if (intValue < 0) {
		    	 intValue = defaultValue;
		     }
		} catch (NumberFormatException e) {
		}
		return intValue;
	}

	/**
	 * Obtains a user based in its auth token
	 * 
	 * @return The user that has been found in the token
	 * @throws ASException
	 *             Returns an exception if no token was found, or if the found
	 *             token is not valid anymore
	 */
	public User getUserFromToken() throws ASException {
		// take the authToken form request
		Map<String,String> headers = this.getHeaders();
		String authToken = headers.get("authtoken");
		if (!StringUtils.hasText(authToken)) {
			Map<String,String> parameters = this.getParameters();
			authToken = parameters.get("authToken");
			if (!StringUtils.hasText(authToken)) {
				throw ASExceptionHelper.authTokenMissingException();
			}
		}
		
		User authUser = null;
		
		try {
			// obtain the use from the token
			authUser = myDao.getByAuthToken(authToken);
			
			if( authUser == null || authUser.getTokenValidity() == null)
				throw ASExceptionHelper.tokenExpiredException();
			
			// validate the token
			Date now = new Date();
			if (authUser.getTokenValidity().before(now)) {
				try {
					// if the token is invalid remove the token and the date and update the user...
					authUser.setAuthToken(null);
					authUser.setTokenValidity(null);
					myDao.update(authUser);
				} catch (Exception e) {}
				//  ...then send a specific error to the client
				throw ASExceptionHelper.tokenExpiredException();
			}
			
			return authUser;
		} catch (ASException e) {
			// re throw the exception
			throw e;
		} catch (Exception e) {
			throw ASExceptionHelper.notAcceptedException();
		}
	}

	/**
	 * Invalidates a login token
	 * 
	 * @throws ASException
	 */
	public void invalidateToken() throws ASException {
		// take the authToken form request
		Map<String,String> headers = this.getHeaders();
		String authToken = headers.get("authtoken");
		if (!StringUtils.hasText(authToken)) {
			Map<String,String> parameters = this.getParameters();
			authToken = parameters.get("authToken");
			if (!StringUtils.hasText(authToken)) {
				throw ASExceptionHelper.authTokenMissingException();
			}
		}

		User authUser = null;
		try {
			// obtain the use from the token
			authUser = myDao.getByAuthToken(authToken);
			
				try {
					// if the token is invalid remove the token and the date and update the user...
					authUser.setAuthToken(null);
					authUser.setTokenValidity(null);
					myDao.update(authUser);
				} catch (Exception e) {
				}
		} catch (ASException e) {
			// re throw the exception
			throw e;
		} catch (Exception e) {
			throw ASExceptionHelper.notAcceptedException();
		}
	}

	/**
	 * Searches the identifierName on the URL using the REST syntax. If found
	 * returns the value on lower case. Otherwise searches the identifier
	 * parameter on the URL's query.
	 * 
	 * @param identifierName
	 *            The identifier to search for
	 * @return The value for the found identifier, or null if it wasn't found
	 * @throws ASException
	 */
	public String obtainIdentifier(String identifierName) throws ASException {
		return this.obtainIdentifier(identifierName, false);
	}
	
	
	/**
	 * Searches the identifierName on the URL using the REST syntax. If found
	 * returns the value on lower case. Otherwise searches the identifier
	 * parameter on the URL's query.
	 * 
	 * @param identifierName
	 *            The identifier to search for
	 * @param caseSensitive
	 *            True if it can be found in a case sensitive way
	 * 
	 * @return The value for the found identifier, or null if it wasn't found
	 * @throws ASException
	 */
	public String obtainIdentifier(String identifierName, boolean caseSensitive) throws ASException {
		String identifierAttribute = (String)getRequest().getAttributes().get(identifierName);
		if (identifierAttribute != null && !"".equals(identifierAttribute)) {
			return (caseSensitive) ? identifierAttribute : identifierAttribute.toLowerCase();
		}
		
		// look on URL parameters
		Map<String,String> parameters = this.getParameters();
		String identifier = parameters.get(identifierName);
		if (identifier == null || "".equals(identifier)) {
			return null;
		}
		return (caseSensitive) ? identifier : identifier.toLowerCase();
	}

	/**
	 * Returns userID from request parameters if exist or from URI path One of
	 * them has to exist.
	 * 
	 * @return
	 * @throws ASException
	 **/
	public String obtainUserIdentifier() throws ASException {
		return obtainUserIdentifier(true);
	}

	/**
	 * Returns userID from request parameters if exist or from URI path One of
	 * them has to exist.
	 * 
	 * @return
	 * @throws ASException
	 **/
	public String obtainUserIdentifier(boolean throwUpOnExpired) throws ASException {
		try {
			String identifierAttribute = (String)getRequest().getAttributes().get(USER_IDENTIFIER_KEY);
			User authUser = this.getUserFromToken();

			if (identifierAttribute != null && !"".equalsIgnoreCase(identifierAttribute)) {
				// check if the caller can access the identifier's data
				if (authHelper.validateIfUserCanAccessOtherUser(authUser, identifierAttribute) == false) {
					throw ASExceptionHelper.forbiddenException();
				}
				return identifierAttribute.toLowerCase();
			}

			// look on URL parameters
			Map<String,String> parameters = this.getParameters();
			String identifier = parameters.get(USER_IDENTIFIER_KEY);
			if (identifier == null || "".equalsIgnoreCase(identifier) || "me".equalsIgnoreCase(identifier)) {
				// If the client does not send the identifier attribute use the authUser.identifier
				identifier = authUser.getIdentifier();
			}
			// check if the caller can access the identifier's data
			if (authHelper.validateIfUserCanAccessOtherUser(authUser, identifier) == false) {
				throw ASExceptionHelper.forbiddenException();
			}
			return identifier.toLowerCase();
		} catch( ASException e ) {
			if ((	e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENEXPIRED_CODE || 
					e.getErrorCode() == ASExceptionHelper.AS_EXCEPTION_AUTHTOKENMISSING_CODE)
					&& !throwUpOnExpired) {
				return null;
			} else {
				throw e;
			}
		}
	}

	/**
	 * Obtains a list of fields available to see for an entity
	 * 
	 * @param bzFields
	 *            The entity descriptor which lists the requested fields
	 * @param level
	 *            The level id
	 * @return A list of permitted fields to return
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String[] obtainOutputFields(final Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		final Object obj = clazz.newInstance();
		final String fields = getParameters().get("fields");
		if(StringUtils.hasText(fields)) {
			List<String> fieldsList = Arrays.asList(fields.split(","));
			String [] obtainedFields = guessGenericFields(obj, "all");
			List<String> ret = CollectionFactory.createList();
			for(String f : obtainedFields ) {
				if(fieldsList.contains(f)) 
					ret.add(f);
			}
			
			return ret.toArray(EMPTY_STRING_ARRAY);
		} else {
			return guessGenericFields(obj, "all");
		}
	}

	/**
	 * Determines a generic field list for an object
	 * 
	 * @param obj
	 *            The object to determine the field list
	 * @param level
	 *            Field level - Deprecated -
	 * @return a String array with all the fields found for the object
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String[] guessGenericFields(final Object obj, final String level) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		@SuppressWarnings("unchecked")
		Map<String, Object> fields = PropertyUtils.describe(obj);
		List<String> outputFields = new ArrayList<String>(fields.keySet());
		outputFields.remove("class");
		Iterator<String> i = fields.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			Object o = fields.get(key);
			if (o != null 
					&& !(o instanceof String) && !(o instanceof Number)
					&& !(o instanceof Date)
					&& !(o instanceof Collection)
					&& !(o instanceof Map )
					&& !(o instanceof Boolean )
					&& !(o instanceof Class)) {
				@SuppressWarnings("unchecked")
				Map<String, Object> subFields = PropertyUtils.describe(o);
				outputFields.remove(key);
				Iterator<String> i2 = subFields.keySet().iterator();
				while(i2.hasNext()) {
					String subKey = i2.next();
					if(!subKey.equals("class"))
						outputFields.add(key + "." + subKey);
				}
			}
		}
		
		List<String> ret = CollectionFactory.createList();
		for( String f : outputFields ) {
			if(!isInvalidField(f))
				ret.add(f);
		}
		
		return ret.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * Generates a safe string format from an object
	 * 
	 * @param from
	 *            The object to generate the string from
	 * @return A safe String representation for the object
	 */
	public Object safeString(Object from) {
		try {
			if( from instanceof String ) {
				return new String(((String)from).getBytes());
			} else {
				return from;
			}
		} catch( Exception e ) {
			logger.log(Level.INFO, e.getMessage(), e);
			return from;
		}
	}
	
	public boolean isInvalidField(String key) {
		if( INVALID_READ_FIELDS.contains(key))
			return true;
		else
			return false;
	}

	public boolean isInvalidFieldForWriting(String key) {
		if( INVALID_WRITE_FIELDS.contains(key))
			return true;
		else
			return false;
	}

	/**
	 * Sets properties of an entity object based in the attributes received in
	 * JSON representation
	 * 
	 * @param jsonObj
	 *            The JSON representation which contains the input data
	 * @param obj
	 *            The object to be modified
	 * @param excludeFields
	 *            a list of fields which cannot be modified
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setPropertiesFromJSONObject(JSONObject jsonObj, Object obj, Set<String> excludeFields) {
		try {
			Map<String, Object> properties = PropertyUtils.describe(obj);
			for (Iterator<String> it = jsonObj.keys(); it.hasNext(); ) {
				try {
					String key = it.next();
					if (excludeFields != null){
						if (!isInvalidFieldForWriting(key)) {
							Object fieldValue = jsonObj.get(key);
							String fieldParts[] = key.split("\\.");
							if( fieldParts.length > 1 ) {
								Object data = PropertyUtils.getProperty(obj, fieldParts[0]);
								StringBuffer subValue = new StringBuffer();
								for( int i = 1; i < fieldParts.length; i++ ) {
									subValue.append(fieldParts[i]);
									if( i < (fieldParts.length - 1)) subValue.append(".");
								}
								JSONObject newObj = new JSONObject();
								newObj.put(subValue.toString(), fieldValue);
								setPropertiesFromJSONObject(newObj, data, excludeFields);
							} else {
								} if (PropertyUtils.getPropertyType(obj, key) == Date.class) {
									String pattern = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern();
									try {
										Date date = DateUtils.parseDate((String)fieldValue, new String[] { pattern });
										PropertyUtils.setProperty(obj, key, date);
									} catch( Exception e ) {
										try {
											Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)fieldValue);
											PropertyUtils.setProperty(obj, key, date);
										} catch( Exception e1) {}
									}
								} else if (PropertyUtils.getPropertyType(obj, key) == HashMap.class) {
									Map<String, Object> props = PropertyUtils.describe(obj);
									HashMap<String,Object> data = (HashMap<String,Object>)props.get(key);
									if( data == null ) data = new HashMap();
									data.clear();
									JSONObject sub = new JSONObject(jsonObj.get(key).toString());
									Iterator<String> x = sub.keys();
									while(x.hasNext()) {
										String sk = x.next();
										Object sv = sub.get(sk);
										data.put(sk, sv);
									}
									PropertyUtils.setProperty(obj, key, data);
								} else if (fieldValue instanceof JSONArray) {
									JSONArray array = (JSONArray)fieldValue;
									Collection<String> col = new ArrayList<String>();
									for (int idx = 0; idx < array.length(); idx++) {
										String value = array.getString(idx);
										col.add(value);
									}
									BeanUtils.setProperty(obj, key, col);
								} else if (fieldValue instanceof JSONObject) {
									Class<?> c = PropertyUtils.getPropertyType(obj, key);
									Object o = properties.get(key);
									if( o == null ) o = c.newInstance();
									setPropertiesFromJSONObject((JSONObject)fieldValue, o, excludeFields);
									BeanUtils.setProperty(obj, key, o);
								} else {
									BeanUtils.setProperty(obj, key, safeString(fieldValue));
								}
							}
					}
				} catch (Exception e) {
					// ignore property
					logger.log(Level.INFO, "Error setting properties from JSON", e);
				}
			}
		} catch (Exception e1) {
			logger.log(Level.INFO, "Error setting properties from JSON", e1);
		}
	}
	
	/**
	 * Converts an entity object into a JSON Object
	 * 
	 * @param input
	 *            The object to convert
	 * @param fields
	 *            Field list to convert
	 * @param lang
	 *            Language to use in multi language wrappers
	 * @return A JSON Representation of the entity input object
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONRepresentationFromObject(Object input, String[] fields) {
		JSONObject jsonobj = new JSONObject();
		try{
			if( input != null ) {
				Map<String, Object> properties = PropertyUtils.describe(input);
				Map<String, List<String>> subFields = CollectionFactory.createMap();
				for( String field : fields ) {
					if( field.contains(".")) {
						String[] sub = field.split("\\.");
						String key = sub[0];
						StringBuffer val = new StringBuffer();
						for( int i = 1; i < sub.length; i++ ) {
							if(i > 1 ) val.append(".");
							val.append(sub[i]);
						}
						List<String> list = subFields.get(key);
						if( list == null ) list = CollectionFactory.createList();
						list.add(val.toString());
						subFields.put(key, list);
					}
				}
				
				for(String fieldName : fields ) {
					if(!fieldName.contains(".")) {
						Object fieldValue = properties.get(fieldName);

						// Process different types for the right output
						if (fieldValue != null) {
							if (fieldValue instanceof Date) {
								fieldValue = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(fieldValue);
							} else if (fieldValue instanceof List ) {
								if(((List<?>)fieldValue).size() > 0 ) {
									Object o = ((List<?>)fieldValue).get(0);
									if(!(o instanceof String) && !(o instanceof Number) && !(o instanceof Key) && !(o instanceof Collection) && !(o instanceof Map)) {
										String[] subFields2 = guessGenericFields(((List<?>)fieldValue).get(0), "all");
										fieldValue = this.getJSONRepresentationFromArrayOfObjects((List<?>)fieldValue, subFields2, fieldName, jsonobj);
									}
								}
							}
							if(!( fieldValue instanceof JSONObject ))
								jsonobj.put(fieldName, fieldValue);
						} else {
							jsonobj.put(fieldName, (String)null);
						}
					}
				}
				
				
				Iterator<String> i = subFields.keySet().iterator();
				while(i.hasNext()) {
					String key = i.next();
					Object fieldValue = properties.get(key);
					JSONObject sub = getJSONRepresentationFromObject(fieldValue, subFields.get(key).toArray(EMPTY_STRING_ARRAY));
					sub.remove("systemDateTime");
					jsonobj.put(key, sub);
				}
						
			}

			this.putSystemDateTime(jsonobj);
		}catch(Exception e){
			e.printStackTrace();
		}

		return jsonobj;
	}
	
	public JSONObject getJSONRepresentationFromArrayOfObjects(List<?>list, String[] fields) {
		return getJSONRepresentationFromArrayOfObjects(list, fields, null, null);
	}

	public JSONObject getJSONRepresentationFromArrayOfObjects(List<?>list, String[] fields, String dataName, JSONObject appender) {
		
		// Tries to add the appender
		JSONObject jsonobj = (appender == null ) ? new JSONObject() : appender;
		
		// if dataName is null... then the simple name is "data"
		String data = dataName == null ? "data" : dataName; 
		
		List<JSONObject> objects = new ArrayList<JSONObject>();
		try {
			if (list != null) {
				for (Iterator<?> it = list.iterator(); it.hasNext();) {
					Object object = it.next();
					JSONObject json = this.getJSONRepresentationFromObject(object, fields);
					objects.add(json);
				}
			}
			jsonobj.put(data, objects);
			jsonobj.put("systemDateTime", DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date()));
		} catch (JSONException e) {
		}
		return jsonobj;
	}

	/**
	 * Converts an ASException into a JSON Object that represents it
	 * 
	 * @param input
	 *            The input Exception
	 * @return A JSON Representation of the exception
	 */
	public JSONObject getJSONRepresentationFromException(ASException input) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("error_message", input.getErrorMessage());
			jsonobj.put("error_code", input.getErrorCode());
			return jsonobj;
		} catch (Exception e) {
		}
		return jsonobj;
	}

	/**
	 * Converts an ASException into a JSON Object that represents it
	 * 
	 * @param input
	 *            The input Exception
	 * @return A JSON Representation of the exception
	 */
	public JSONObject getJSONRepresentationFromException(Exception input) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("error_message", input.getLocalizedMessage());
			jsonobj.put("error_code", 500);
		} catch (JSONException e) {
		}
		return jsonobj;
	}

	/**
	 * Returns the URI for the current request
	 * 
	 * @return The URI for the current request
	 */
	public String getRequestURI() {
		try {
			return ServletUtils.getRequest(getRequest()).getRequestURI();
		} catch( Exception e ) {
			return "/null";
		}
	}
	
	/**
	 * Returns the full request URI for the current request
	 * 
	 * @return The full request URI for the current request
	 */
	public String getFullRequestURI() {
		try {
			return ServletUtils.getRequest(getRequest()).getRequestURL().toString();
		} catch( Exception e ) {
			return "/null";
		}
	}

	/**
	 * Returns the IP address from where a request was originated
	 * 
	 * @return The IP address from where the request was originated
	 */
	public String getRequestIP() {
		try {
			return ServletUtils.getRequest(getRequest()).getRemoteAddr();
		} catch( Exception e ) {
			return "/null";
		}
	}

	/**
	 * Returns the HTTP user agent from a request
	 * 
	 * @return A String with the HTTP user agent
	 */
	public String getRequestAgent() {
		try {
			return ServletUtils.getRequest(getRequest()).getHeader("User-Agent");
		} catch( Exception e ) {
			return "/null";
		}
	}

	/**
	 * Logs a transaction as started
	 * 
	 * @return A unix time (milliseconds) for the transaction start
	 */
	public long markStart() {
		return markStart(null);
	}
	
	/**
	 * Logs a transaction as started
	 * 
	 * @param text
	 *            Text to log
	 * 
	 * @return A unix time (milliseconds) for the transaction start
	 */
	public long markStart(String text) {
		logger.log(Level.INFO, "Request " + getRequestURI() + " begins...");
		return System.currentTimeMillis();
	}
	
	/**
	 * Logs a transaction as ended
	 * 
	 * @param start
	 *            The unix time of the transaction start
	 * @return A unix time (millisendos) for the transaction end
	 */
	public long markEnd(long start) {
		return markEnd(null, start);
	}

	/**
	 * Logs a transaction as ended
	 * 
	 * @param text
	 *            Text to log
	 * @param start
	 *            The unix time of the transaction start
	 * @return A unix time (millisendos) for the transaction end
	 */
	public long markEnd(String text, long start) {
		long end = new Date().getTime();
		long time = end - start;
		logger.log(Level.INFO, "Request " + getRequestURI() + " ended in " + time + "ms.");
		return end;
	}
}
