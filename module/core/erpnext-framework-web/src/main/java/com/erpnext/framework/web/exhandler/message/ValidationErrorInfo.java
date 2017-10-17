package com.erpnext.framework.web.exhandler.message;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorInfo extends ErrorInfo {
	
	private List<Error> errors = new ArrayList<>(6);
	
	public ValidationErrorInfo(ErrorInfo orig) {
        super(orig);
    }
	
	public ValidationErrorInfo addError(String field, Object rejectedValue, String message) {
        errors.add(new Error(field, rejectedValue, message));
        return this;
    }

    public ValidationErrorInfo addError(String message) {
        errors.add(new Error(null, null, message));
        return this;
    }
	
	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public static class Error {
		private final String field;
        private final Object rejected;
        private final String message;
        
		public Error(String field, Object rejected, String message) {
			super();
			this.field = field;
			this.rejected = rejected;
			this.message = message;
		}

		public String getField() {
			return field;
		}

		public Object getRejected() {
			return rejected;
		}

		public String getMessage() {
			return message;
		}
        
	}

}
