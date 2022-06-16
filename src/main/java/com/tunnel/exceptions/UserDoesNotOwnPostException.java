package com.tunnel.exceptions;

public class UserDoesNotOwnPostException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDoesNotOwnPostException(String message) {
		super(message);
	}
	
	

}
