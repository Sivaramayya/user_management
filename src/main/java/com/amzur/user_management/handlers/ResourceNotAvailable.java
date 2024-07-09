package com.amzur.user_management.handlers;

public class ResourceNotAvailable extends RuntimeException{

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotAvailable (String message) {
		super(message);
	}
	
}
