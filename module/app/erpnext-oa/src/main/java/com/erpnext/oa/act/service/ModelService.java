package com.erpnext.oa.act.service;

import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;

public interface ModelService {
	
	Model getModel(String modelId);
	
	boolean validateModelKey(String modelKey); 
	
	Model createModel(ModelDTO modelDTO,String editorJson);

}
