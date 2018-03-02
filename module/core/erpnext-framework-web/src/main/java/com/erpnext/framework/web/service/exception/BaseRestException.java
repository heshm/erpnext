package com.erpnext.framework.web.service.exception;

import java.util.HashMap;
import java.util.Map;

public class BaseRestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String messageKey;
	protected Map<String, Object> customData;
    

    public BaseRestException() {
        super();
    }
    
    public BaseRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRestException(String message) {
        super(message);
    }

    public BaseRestException(Throwable cause) {
        super(cause);
    }
    
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
    
    public String getMessageKey() {
        return messageKey;
    }

	public Map<String, Object> getCustomData() {
		return customData;
	}

	public void setCustomData(Map<String, Object> customData) {
		this.customData = customData;
	}
    
	public void addCustomData(String key, Object data) {
		if (customData == null) {
			customData = new HashMap<String, Object>();
		}
		customData.put(key, data);
	}

}
