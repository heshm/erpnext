package com.erpnext.oa.act.dto;

import java.util.Map;

public class SaveFormRepresentation {
	
	protected String formId;
    protected Map<String, Object> values;

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

}
