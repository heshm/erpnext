package com.erpnext.oa.act.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.framework.util.IDUtils;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;
import com.erpnext.oa.act.mapper.ModelMapper;

@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {
	
	@Autowired
	private ModelMapper modelMapper;

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
	    modelMapper.insert(newModel);
		return newModel;
	}

}
