package com.erpnext.oa.act.service;

import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.util.IDUtils;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;
import com.erpnext.oa.act.mapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {
	
	private final Logger log = LoggerFactory.getLogger(ModelServiceImpl.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ModelImageService modelImageService;

	@Override
	public Model getModel(String modelId) {
		return modelMapper.selectByPrimaryKey(modelId);
	}

	@Override
	public boolean validateModelKey(String modelKey) {
		return modelMapper.selectByModelKey(modelKey) != null;
	}

	@Override
	@Transactional
	public Model createModel(ModelDTO model, String editorJson) {
		Model newModel = new Model();
	    newModel.setVersion(1);
	    newModel.setName(model.getName());
	    newModel.setModelKey(model.getKey());
	    newModel.setModelType(model.getModelType());
	    newModel.setCreated(Calendar.getInstance().getTime());
	    newModel.setCreatedBy(AuthenticationUtils.getPrincipal().getUsername());
	    newModel.setDescription(model.getDescription());
	    newModel.setModelEditorJson(editorJson);
	    newModel.setLastUpdated(Calendar.getInstance().getTime());
	    newModel.setLastUpdatedBy(AuthenticationUtils.getPrincipal().getUsername());
	    newModel.setId(IDUtils.uuid());
	    modelMapper.insert(persistModel(newModel));
		return newModel;
	}
	
	private Model persistModel(Model model) {
		if (!StringUtils.isEmpty(model.getModelEditorJson())) {
			ObjectNode jsonNode = null;
			try {
				jsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
			} catch (IOException e) {
				log.warn("Could not deserialize json model");
			}
			if ((model.getModelType() == null || model.getModelType().intValue() == Model.MODEL_TYPE_BPMN)) {
				modelImageService.generateThumbnailImage(model, jsonNode);
			}else if (model.getModelType().intValue() == Model.MODEL_TYPE_FORM || 
			          model.getModelType().intValue() == Model.MODEL_TYPE_DECISION_TABLE) {
		        jsonNode.put("name", model.getName());
		        jsonNode.put("key", model.getModelKey());
		    }
		}
		return model;
	}

}
