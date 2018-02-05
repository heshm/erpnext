package com.erpnext.oa.act.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;

import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;
import com.erpnext.oa.act.dto.ResultListDataDTO;

public interface ModelService {
	
	Model getModel(String modelId);
	
	ResultListDataDTO getModel(String filter, String sort, Integer modelType, HttpServletRequest request);
	
	boolean validateModelKey(String modelKey); 
	
	Model createModel(ModelDTO modelDTO,String editorJson);
	
	Model saveModel(Model modelObject);
	
	Model saveModel(Model modelObject, String editorJson, byte[] imageBytes, boolean newVersion, String newVersionComment, UserDetails updatedBy);

	Model saveModel(String modelId, String name, String key, String description, String editorJson, 
	      boolean newVersion, String newVersionComment, UserDetails updatedBy);


}
