package com.erpnext.framework.web.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseEndpoint {
	
	protected Logger logger = LoggerFactory.getLogger(BaseEndpoint.class);
	
	protected final static String DELETED = "deleted";
	
	protected final static String CREATED = "created";
	
	protected final static String UPDATED = "updated";

}
