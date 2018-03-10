package com.ciessa.museum.model;

import java.io.Serializable;

public class SystemConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	private int authTokenValidityInDays;
	private int defaultFromRange;
	private int defaultToRange;

	public int getAuthTokenValidityInDays() {
		return authTokenValidityInDays;
	}

	public void setAuthTokenValidityInDays(int authTokenValidityInDays) {
		this.authTokenValidityInDays = authTokenValidityInDays;
	}

	public int getDefaultFromRange() {
		return defaultFromRange;
	}

	public void setDefaultFromRange(int defaultFromRange) {
		this.defaultFromRange = defaultFromRange;
	}

	public int getDefaultToRange() {
		return defaultToRange;
	}

	public void setDefaultToRange(int defaultToRange) {
		this.defaultToRange = defaultToRange;
	}

}
