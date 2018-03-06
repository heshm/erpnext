package com.erpnext.oa.act.dto;

public class FormSaveRepresentation {
	
	private boolean reusable;
	private boolean newVersion;
	private String comment;
	private String formImageBase64;
	private FormRepresentation formRepresentation;
    
    public boolean isReusable() {
        return reusable;
    }
    public void setReusable(boolean reusable) {
        this.reusable = reusable;
    }
    public boolean isNewVersion() {
        return newVersion;
    }
    public void setNewVersion(boolean newVersion) {
        this.newVersion = newVersion;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getFormImageBase64() {
		return formImageBase64;
	}
	public void setFormImageBase64(String formImageBase64) {
		this.formImageBase64 = formImageBase64;
	}
	public FormRepresentation getFormRepresentation() {
        return formRepresentation;
    }
    public void setFormRepresentation(FormRepresentation formRepresentation) {
        this.formRepresentation = formRepresentation;
    }

}
