package com.ciessa.museum.model;

import java.io.Serializable;
import java.util.Date;

public class DataSet implements Serializable, ModelKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6373618590418911361L;

	public static final String DEFAULT_DS_KEY = "00000000-0000-0000-0000-000000000001";

	public static final int STATUS_ENABLED = 1;
	public static final int STATUS_DISABLED = 1;

	private String key;
	private String name;
	private String description;

	private String driverClass;
	private String url;
	private String username;
	private String password;
	private String defaultSchema;
	private String defaultCatalog;
	private String dialect;

	private Date creationDateTime;
	private Date lastUpdate;
	private int status;

	public DataSet(String key, String name, String description, String driverClass, String url, String username,
			String password, String defaultSchema, String defaultCatalog, String dialect, Date creationDateTime,
			Date lastUpdate, int status) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
		this.driverClass = driverClass;
		this.url = url;
		this.username = username;
		this.password = password;
		this.defaultSchema = defaultSchema;
		this.defaultCatalog = defaultCatalog;
		this.dialect = dialect;
		this.creationDateTime = creationDateTime;
		this.lastUpdate = lastUpdate;
		this.status = status;
	}

	public DataSet() {
		super();
		this.creationDateTime = new Date();
		this.lastUpdate = new Date();
		this.status = STATUS_ENABLED;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return driverClass;
	}

	/**
	 * @param driverClass
	 *            the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the defaultSchema
	 */
	public String getDefaultSchema() {
		return defaultSchema;
	}

	/**
	 * @param defaultSchema
	 *            the defaultSchema to set
	 */
	public void setDefaultSchema(String defaultSchema) {
		this.defaultSchema = defaultSchema;
	}

	/**
	 * @return the defaultCatalog
	 */
	public String getDefaultCatalog() {
		return defaultCatalog;
	}

	/**
	 * @param defaultCatalog
	 *            the defaultCatalog to set
	 */
	public void setDefaultCatalog(String defaultCatalog) {
		this.defaultCatalog = defaultCatalog;
	}

	/**
	 * @return the dialect
	 */
	public String getDialect() {
		return dialect;
	}

	/**
	 * @param dialect
	 *            the dialect to set
	 */
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
		DataSet other = (DataSet) obj;
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
		return "DataSet [key=" + key + ", name=" + name + ", description=" + description + "]";
	}

}
