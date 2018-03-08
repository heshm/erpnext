package com.erpnext.oa.act.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.bpmn.model.BpmnModel;
import org.springframework.security.core.userdetails.UserDetails;

import com.erpnext.oa.act.domain.AbstractModel;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelRepresentation;
import com.erpnext.oa.act.dto.ResultListDataRepresentation;

public interface ModelService {
	
	Model getModel(String modelId);
	
	ResultListDataRepresentation getModel(String filter, String sort, Integer modelType, HttpServletRequest request);
	
	ResultListDataRepresentation getModelHistory(String modelId, boolean includeLatestVersion);
	
	List<ModelRepresentation> getModel(String appId,String filter,Integer modelType);
	
	/**
	 * 模型存在返回true
	 * @param 模型键值
	 * @return 模型是否存在
	 */
	boolean validateModelKey(String modelKey); 
	
	Model createModel(ModelRepresentation modelDTO,String editorJson);
	
	Model saveModel(Model modelObject);
	
	Model saveModel(Model modelObject, String editorJson, byte[] imageBytes, boolean newVersion, String newVersionComment, UserDetails updatedBy);

	Model saveModel(String modelId, String name, String key, String description, String editorJson, 
	      boolean newVersion, String newVersionComment, UserDetails updatedBy);
	
	BpmnModel getBpmnModel(AbstractModel model);
	
	BpmnModel getBpmnModel(AbstractModel model, Map<String, Model> formMap, Map<String, Model> decisionTableMap);
	
	byte[] getBpmnXML(BpmnModel bpmnModel);
	
	void updateCategory(String modelId,String appId);


}
