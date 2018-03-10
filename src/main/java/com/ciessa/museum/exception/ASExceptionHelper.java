package com.ciessa.museum.exception;

import java.util.List;

public class ASExceptionHelper {
	public static int AS_EXCEPTION_FORBIDDEN_CODE = 403;
	public static int AS_EXCEPTION_NOTFOUND_CODE = 404;
	public static int AS_EXCEPTION_ALREADYEXISTS_CODE = 405;
	public static int AS_EXCEPTION_NOTACCEPTED_CODE = 406;
	public static int AS_EXCEPTION_INVALIDARGUMENTS_CODE = 407;
	public static int AS_EXCEPTION_AUTHTOKENEXPIRED_CODE = 408;
	public static int AS_EXCEPTION_AUTHTOKENMISSING_CODE = 409;
	public static int AS_EXCEPTION_CONCURRENT_MODIFICATION_CODE = 410;
	public static int AS_EXCEPTION_MAILALREADYEXISTS_CODE = 411;
	public static int AS_EXCEPTION_NOT_UNIQUE_CODE = 412;
	public static int AS_EXCEPTION_PUSH_MESSAGE = 413;
	public static int AS_EXCEPTION_INTERNALERROR_CODE = 500;
	
	public static ASException forbiddenException() {
		return new ASException("Forbidden", AS_EXCEPTION_FORBIDDEN_CODE);
	}
	public static ASException notFoundException() {
		return new ASException("Not found", AS_EXCEPTION_NOTFOUND_CODE);
	}
	public static ASException notFoundException(String data) {
		return new ASException("Entity Id " + data + " Not found", AS_EXCEPTION_NOTFOUND_CODE);
	}
	public static ASException alreadyExistsException() {
		return new ASException("Entity already exists", AS_EXCEPTION_ALREADYEXISTS_CODE);
	}
	public static ASException notAcceptedException() {
		return new ASException("Not accepted", AS_EXCEPTION_NOTACCEPTED_CODE);
	}
	public static ASException invalidArgumentsException(List<String> invalidFields) {
		return ASExceptionHelper.invalidArgumentsException(invalidFields.toString());
	}
	public static ASException invalidArgumentsException(String invalidField) {
		StringBuffer sb = new StringBuffer("Invalid Arguments on fields:");
		sb.append(invalidField.toString());	
		return new ASException(sb.toString(), AS_EXCEPTION_INVALIDARGUMENTS_CODE);
	}
	public static ASException invalidArgumentsException() {
		return new ASException("Invalid Arguments", AS_EXCEPTION_INVALIDARGUMENTS_CODE);
	}
	public static ASException tokenExpiredException() {
		return new ASException("Auth token expired", AS_EXCEPTION_AUTHTOKENEXPIRED_CODE);
	}
	public static ASException authTokenMissingException() {
		return new ASException("Auth token missing", AS_EXCEPTION_AUTHTOKENMISSING_CODE);
	}
	public static ASException defaultException(String message, Throwable cause) {
		return new ASException(message, AS_EXCEPTION_INTERNALERROR_CODE, cause);
	}
	public static ASException concurrentModificationException() {
		return new ASException("Concurrent error", AS_EXCEPTION_CONCURRENT_MODIFICATION_CODE);
	}
	public static ASException mailAlreadyExistsException() {
		return new ASException("Mail already exists", AS_EXCEPTION_MAILALREADYEXISTS_CODE);
	}
	public static ASException notUniqueException() {
		return new ASException("Not Unique value found", AS_EXCEPTION_NOT_UNIQUE_CODE);
	}
	public static ASException pushMessageException(String userId, String title) {
		return new ASException("Push Message not delivered to user " + userId  
			+ " and title " + title, AS_EXCEPTION_PUSH_MESSAGE);
	}
}
