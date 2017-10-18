package com.erpnext.framework.web.endpoint;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.erpnext.framework.web.exhandler.message.ErrorInfo;
import com.erpnext.framework.web.exhandler.message.ValidationErrorInfo;
import com.erpnext.framework.web.interpolator.MessageInterpolator;
import com.erpnext.framework.web.interpolator.SpelMessageInterpolator;
import com.erpnext.framework.web.util.AuthenticationUtils;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandlerEndpoint extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandlerEndpoint.class);
	
	private static final String 
		ERROR_KEY = "error",
		DETAIL_KEY = "detail";
	
	@Autowired
	private MessageSource messageSource;
	
	private MessageInterpolator interpolator = new SpelMessageInterpolator();
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ValidationErrorInfo errInfo = createBody(ex,status,request,ex.getBindingResult());
		return handleExceptionInternal(ex, errInfo, headers, status, request);
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request){
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErrorInfo m = createBody(ex,status,request);
		return handleExceptionInternal(ex, m, null, status, request);
	}
	
	private ValidationErrorInfo createBody(Exception ex,HttpStatus status,WebRequest request,BindingResult result){
		ErrorInfo m = createBody(ex,status,request);
		ValidationErrorInfo msg = new ValidationErrorInfo(m);
		for(ObjectError err : result.getGlobalErrors()){
			msg.addError(err.getDefaultMessage());
		}
        for (FieldError err : result.getFieldErrors()) {
            msg.addError(err.getField(), err.getRejectedValue(), err.getDefaultMessage());
        }
        return msg;
	}
	
	private ErrorInfo createBody(Exception ex,HttpStatus status,WebRequest request){
		ErrorInfo m = new ErrorInfo();
		Locale locale = request.getLocale();
		m.setError(resolveMessage(ERROR_KEY,ex,locale));
		if(ex instanceof AccessDeniedException){
			String username = AuthenticationUtils.getPrincipal().getUsername();
			m.setDetail(getMessage(DETAIL_KEY,ex,locale,username));
		}else{
			m.setDetail(resolveMessage(DETAIL_KEY,ex,locale));
		}
		m.setStatus(status);
		return m;
	}
	
	private String resolveMessage(String key, Exception exception,Locale locale){
		 String template = getMessage(key, exception,locale);
		 Map<String, Object> vars = new HashMap<>(1);
	     vars.put("ex", exception);
	     return interpolateMessage(template, vars);
	}
	
	private String interpolateMessage(String messageTemplate, Map<String, Object> variables){
		return interpolator.interpolate(messageTemplate, variables);
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
