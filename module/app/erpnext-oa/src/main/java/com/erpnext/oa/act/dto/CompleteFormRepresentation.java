package com.erpnext.oa.act.dto;

import java.util.Map;

public class CompleteFormRepresentation {

	protected String formId;
	protected Map<String, Object> values;
	protected String outcome;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}
