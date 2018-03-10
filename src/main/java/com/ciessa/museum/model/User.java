package com.ciessa.museum.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.StringUtils;

public class User implements Serializable, ModelKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7329891425925565361L;

	public static final String DEFAULT_ADMIN_KEY = "00000000-0000-0000-0000-000000000001";

	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_USER = 2;

	public static final int STATUS_ENABLED = 1;
	public static final int STATUS_DISABLED = 1;

	private String key;

	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int role;
	private int status;
	private String defaultDataSet;

	private String authToken;
	private Date tokenValidity;

	private Date creationDateTime;
	private Date lastUpdate;
	private Date lastLogin;

	public User() {
		super();
		this.creationDateTime = new Date();
		this.lastUpdate = new Date();
		this.role = ROLE_USER;
		this.status = STATUS_ENABLED;
	}

	public User(String key, String login, String password, String firstname, String lastname, String email, int role,
			int status, String defaultDataSet, String authToken, Date tokenValidity, Date creationDateTime,
			Date lastUpdate, Date lastLogin) {
		super();
		this.key = key;
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.status = status;
		this.defaultDataSet = defaultDataSet;
		this.authToken = authToken;
		this.tokenValidity = tokenValidity;
		this.creationDateTime = creationDateTime;
		this.lastUpdate = lastUpdate;
		this.lastLogin = lastLogin;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken
	 *            the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the tokenValidity
	 */
	public Date getTokenValidity() {
		return tokenValidity;
	}

	/**
	 * @param tokenValidity
	 *            the tokenValidity to set
	 */
	public void setTokenValidity(Date tokenValidity) {
		this.tokenValidity = tokenValidity;
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

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate
	 *            the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the defaultDataSet
	 */
	public String getDefaultDataSet() {
		return defaultDataSet;
	}

	/**
	 * @param defaultDataSet
	 *            the defaultDataSet to set
	 */
	public void setDefaultDataSet(String defaultDataSet) {
		this.defaultDataSet = defaultDataSet;
	}

	public String getFullname() {
		StringBuffer sb = new StringBuffer();
		
		if(StringUtils.hasText(firstname))
			sb.append(firstname).append(" ");
		if(StringUtils.hasText(lastname))
			sb.append(lastname);
		
		if(sb.length() == 0)
			sb.append(login);
		
		return sb.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [key=" + key + ", login=" + login + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", status=" + status + "]";
	}

}
