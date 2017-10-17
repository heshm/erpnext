package com.erpnext.framework.web.endpoint;

import java.util.Locale;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	private static final String 
		TITLE_KEY = "title",
		DETAIL_KEY = "detail";
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return null;
	}
	
	private String getMessage(String key,Exception ex,Locale locale) {
		return getMessage(key,ex,locale,"");
	}
	
	private String getMessage(String key,Exception ex,Locale locale,Object ... args) {
		String prefix = ex.getClass().getName();
		String code = prefix + "." + key;
		String message = messageSource.getMessage(code , args, null, locale);
		if(message == null){
			message = "";
			if(logger.isDebugEnabled()) {
				logger.debug("No message found for {}.{}.", prefix, key);
			}
		}
		return message;
	}

}
