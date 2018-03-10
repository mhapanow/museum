package com.ciessa.museum.exception;

public class ASException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private int errorCode;
	
	public ASException() {
		super();
	}

	public ASException(String errorMessage, int errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public ASException(String errorMessage, int errorCode, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return this.errorCode;
	}
}
