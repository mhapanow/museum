package com.ciessa.museum.model;

import java.io.Serializable;
import java.util.Date;

public class AuditEntry implements Serializable, ModelKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2275648813432384171L;

	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_LOGOUT = "logout";
	
	private String key;
	private String login;
	private String action;
	private String module;
	private String description;
	private Date creationDateTime;

	public AuditEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuditEntry(String key, String login, String action, String module, String description,
			Date creationDateTime) {
		super();
		this.key = key;
		this.login = login;
		this.action = action;
		this.module = module;
		this.description = description;
		this.creationDateTime = creationDateTime;
	}

	/**
	 * @return the key
	 */
	public String getIdentifier() {
		return key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the creationDateTime
	 */
	public Date getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * @param creationDateTime
	 *            the creationDateTime to set
	 */
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

}
