package com.erpnext.oa.act.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.form.model.FormContainer;
import org.flowable.form.model.FormField;
import org.flowable.form.model.FormOutcome;

public class SimpleFormModel {
	
	protected String name;
    protected String key;
    protected int version;
    protected String description;
    protected List<FormField> fields;
    protected List<FormOutcome> outcomes;
    protected String outcomeVariableName;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Do not use this method for logical operations since it only return the top level fields. I.e. A "container" field's sub fields are not returned. For verifying and listing all fields from a form
     * use instead listAllFields().
     *
     * @return The top level fields, a container's sub fields are not returned.
     */
    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> fields) {
        this.fields = fields;
    }

    public List<FormOutcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<FormOutcome> outcomes) {
        this.outcomes = outcomes;
    }

    public String getOutcomeVariableName() {
        return outcomeVariableName;
    }

    public void setOutcomeVariableName(String outcomeVariableName) {
        this.outcomeVariableName = outcomeVariableName;
    }

    /*
     * Helper methods
     */
    public Map<String, FormField> allFieldsAsMap() {
        Map<String, FormField> result = new HashMap<>();
        List<FormField> allFields = listAllFields();
        if (allFields != null) {
            for (FormField field : allFields) {
                if (!result.containsKey(field.getId())) {

                    result.put(field.getId(), field);
                }
            }
        }
        return result;
    }

    public List<FormField> listAllFields() {
        List<FormField> listOfAllFields = new ArrayList<>();
        collectSubFields(fields, listOfAllFields);
        return listOfAllFields;
    }

    protected void collectSubFields(List<FormField> fields, List<FormField> listOfAllFields) {
        if (fields != null && fields.size() > 0) {
            for (FormField field : fields) {
                listOfAllFields.add(field);
                if (field instanceof FormContainer) {
                    FormContainer container = (FormContainer) field;
                    List<List<FormField>> subFields = container.getFields();
                    if (subFields != null) {
                        for (List<FormField> subFieldDefinitions : subFields) {
                            if (subFieldDefinitions != null) {
                                collectSubFields(subFieldDefinitions, listOfAllFields);
                            }
                        }
                    }
                }
            }
        }
    }

}
