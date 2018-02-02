package com.erpnext.oa.act.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.controller.BaseController;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.service.ModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/oa/act/app")
public class ModelController extends BaseController{
	
	@Autowired
	private ModelService modelService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/rest/models/{modelId}/editor/json")
	public ObjectNode getModelJSON(@PathVariable String modelId) throws Exception {
		Model model = modelService.getModel(modelId);
		ObjectNode modelNode = objectMapper.createObjectNode();
	    modelNode.put("modelId", model.getId());
	    modelNode.put("name", model.getName());
	    modelNode.put("key", model.getModelKey());
	    modelNode.put("description", model.getDescription());
	    modelNode.putPOJO("lastUpdated", model.getLastUpdated());
	    modelNode.put("lastUpdatedBy", model.getLastUpdatedBy());
	    if(StringUtils.isEmpty(model.getModelEditorJson())) {
	    	ObjectNode editorJsonNode = objectMapper.createObjectNode();
	        editorJsonNode.put("id", "canvas");
	        editorJsonNode.put("resourceId", "canvas");
	        ObjectNode stencilSetNode = objectMapper.createObjectNode();
	        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	        editorJsonNode.put("modelType", "model");
	        modelNode.set("model", editorJsonNode);
	    }else {
	    	ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
	        editorJsonNode.put("modelType", "model");
	        modelNode.set("model", editorJsonNode);
	    }
		return modelNode;
	}

}
