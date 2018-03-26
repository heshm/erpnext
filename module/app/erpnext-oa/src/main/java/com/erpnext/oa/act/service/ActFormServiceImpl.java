package com.erpnext.oa.act.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.model.FormModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.web.service.exception.InternalServerErrorException;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.domain.AbstractModel;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.domain.SimpleFormModel;
import com.erpnext.oa.act.dto.FormRepresentation;
import com.erpnext.oa.act.dto.FormSaveRepresentation;
import com.erpnext.oa.act.dto.ResultListDataRepresentation;
import com.erpnext.oa.act.mapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional(readOnly = true)
public class ActFormServiceImpl implements ActFormService {

	private Logger logger = LoggerFactory.getLogger(ActFormServiceImpl.class);

	@Resource
	private ModelService modelService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FormRepositoryService formRepositoryService;


	@Override
	public FormRepresentation getForm(String formId) {
		Model model = modelService.getModel(formId);
		FormRepresentation form = createFormRepresentation(model);
		return form;
	}

	@Override
	@Transactional
	public FormRepresentation saveForm(String formId, FormSaveRepresentation saveRepresentation) {
		UserDetails user = AuthenticationUtils.getPrincipal();
		Model model = modelService.getModel(formId);

		String formKey = saveRepresentation.getFormRepresentation().getKey();

		model.setName(saveRepresentation.getFormRepresentation().getName());
		model.setModelKey(formKey);
		model.setDescription(saveRepresentation.getFormRepresentation().getDescription());

		String editorJson = null;
		try {
			editorJson = objectMapper
					.writeValueAsString(saveRepresentation.getFormRepresentation().getFormDefinition());
		} catch (Exception e) {
			logger.error("Error while processing form json", e);
			throw new InternalServerErrorException("Form could not be saved " + formId);
		}

		String filteredImageString = saveRepresentation.getFormImageBase64().replace("data:image/png;base64,", "");
		byte[] imageBytes = Base64.decodeBase64(filteredImageString);
		model = modelService.saveModel(model, editorJson, imageBytes, saveRepresentation.isNewVersion(),
				saveRepresentation.getComment(), user);
		FormRepresentation result = new FormRepresentation(model);
		result.setFormDefinition(saveRepresentation.getFormRepresentation().getFormDefinition());
		return result;

	}

	@Override
	public ResultListDataRepresentation getForms(String filter) {
		List<Model> modelList = modelMapper.selectModelList(null, AbstractModel.MODEL_TYPE_FORM, filter, null);
		List<FormRepresentation> formList = new ArrayList<FormRepresentation>();
		modelList.forEach(model -> {
			formList.add(new FormRepresentation(model));
		});
		ResultListDataRepresentation result = new ResultListDataRepresentation(formList);
		result.setTotal(Long.valueOf(formList.size()));
		return result;
	}

	private FormRepresentation createFormRepresentation(AbstractModel model) {
		SimpleFormModel formDefinition = null;
		try {
			formDefinition = objectMapper.readValue(model.getModelEditorJson(), SimpleFormModel.class);
		} catch (Exception e) {
			logger.error("Error deserializing form", e);
			throw new InternalServerErrorException("Could not deserialize form definition");
		}

		FormRepresentation result = new FormRepresentation(model);
		result.setFormDefinition(formDefinition);
		return result;
	}

	@Override
	public FormModel getProcessDefinitionStartForm(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
		FormModel formInfo = null;
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        org.flowable.bpmn.model.Process process = bpmnModel.getProcessById(processDefinition.getKey());
        FlowElement startElement = process.getInitialFlowElement();
        if (startElement instanceof StartEvent) {
            StartEvent startEvent = (StartEvent) startElement;
            if (!StringUtils.isEmpty(startEvent.getFormKey())) {
                formInfo = formRepositoryService.getFormModelByKeyAndParentDeploymentId(startEvent.getFormKey(),
                        processDefinition.getDeploymentId(), processDefinition.getTenantId());
            }
        }
		return formInfo;
	}

}
