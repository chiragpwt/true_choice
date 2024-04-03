package com.truechoice.exceptionhandling;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

	/**
	 * 
	 */

	private static final long serialVersionUID = -1102577956890084829L;
	private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}
	
	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		// TODO Auto-generated method stub
		return httpStatus;
	}
	
	public CustomException(String message) {
		super(message);
	}

}
