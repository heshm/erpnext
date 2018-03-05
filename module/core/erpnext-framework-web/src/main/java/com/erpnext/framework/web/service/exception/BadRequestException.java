package com.erpnext.framework.web.service.exception;

public class BadRequestException extends BaseRestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String s) {
		super(s);
	}
	
	public BadRequestException(String message, String messageKey) {
	    this(message);
	    setMessageKey(messageKey);
	}
	
	public BadRequestException(String s, Throwable t) {
        super(s, t);
    }

}
