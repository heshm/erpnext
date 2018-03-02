package com.erpnext.framework.web.service.exception;

public class InternalServerErrorException extends BaseRestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InternalServerErrorException() {
	}

	public InternalServerErrorException(String message) {
		super(message);
	}
	
	public InternalServerErrorException(String message, Throwable t) {
		super(message, t);
	}

}
