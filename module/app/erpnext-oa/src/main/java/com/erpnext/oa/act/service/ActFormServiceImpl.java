package com.erpnext.oa.act.service;

import javax.annotation.Resource;

import org.activiti.form.model.FormDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.framework.web.service.exception.InternalServerErrorException;
import com.erpnext.oa.act.domain.AbstractModel;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.FormDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional(readOnly = true)
public class ActFormServiceImpl implements ActFormService {
	
	private Logger logger = LoggerFactory.getLogger(ActFormServiceImpl.class);

	@Resource
	private ModelService modelService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public FormDTO getForm(String formId) {
		Model model = modelService.getModel(formId);
		FormDTO form = createFormRepresentation(model);
	    return form;
	}

	private FormDTO createFormRepresentation(AbstractModel model) {
		FormDefinition formDefinition = null;
		try {
			formDefinition = objectMapper.readValue(model.getModelEditorJson(), FormDefinition.class);
		} catch (Exception e) {
			logger.error("Error deserializing form", e);
			throw new InternalServerErrorException("Could not deserialize form definition");
		}

		FormDTO result = new FormDTO(model);
		result.setFormDefinition(formDefinition);
		return result;
	}

}
