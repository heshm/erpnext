package com.erpnext.oa.act.service;

import com.erpnext.oa.act.dto.FormRepresentation;
import com.erpnext.oa.act.dto.FormSaveRepresentation;
import com.erpnext.oa.act.dto.ResultListDataRepresentation;

public interface ActFormService {
	
	FormRepresentation getForm(String formId);
	
	FormRepresentation saveForm(String formId,FormSaveRepresentation formSaveRepresentation);
	
	ResultListDataRepresentation getForms(String filter);

}
