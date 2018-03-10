package com.ciessa.museum.model;

import java.util.Date;

public interface ModelKey {

	public String getIdentifier();
	public void setCreationDateTime(Date creationDateTime);
	public void setLastUpdate(Date lastUpdate);
	public void setKey(String key);
	
}
