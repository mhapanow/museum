package com.ciessa.museum.tools;

import java.util.regex.Pattern;

/**
 * Simple validators
 * Test the regular expressions on:
 * http://www.regextester.com/
 * http://stackoverflow.com/questions/845317/regex-solution-for-objective-c
 */
public class CommonValidator {
	
	/**
	 * Validates the identifier for:
	 * must be UNIQUE
	 * length:[3-100]
	 * content:[A-Za-z0-9.@#_-&$]
	 * case insensitive
	 * @param identifier
	 * @return
	 */
	public boolean validateIdentifier(String identifier) {
		if (identifier == null) {
			return false;
		}

		Pattern pattern = Pattern.compile("[a-zA-Z0-9\\_\\-\\#\\@\\$\\.\\&]+");
    	String []result = pattern.split(identifier);
    	if (result.length > 0) {
    		// contains invalid characters
    		return false;
    	}		
    	int length  = identifier.length();
    	if (length < 3 || length > 100) {
    		// invalid length
    		return false;
    	}
    	return true;
	}

	/**
	 * Validates an email address
	 * http://www.regular-expressions.info/email.html
	 * @param address
	 * @return
	 */
	public boolean validateEmailAddress(String address) {
		if (address == null) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    	String []result = pattern.split(address);
    	if (result.length > 0) {
    		// contains invalid characters
    		return false;
    	}		
    	return true;
	}
}
