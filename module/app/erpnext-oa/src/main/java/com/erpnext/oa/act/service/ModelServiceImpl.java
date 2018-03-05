package com.erpnext.oa.act.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.util.IDUtils;
import com.erpnext.framework.web.service.exception.InternalServerErrorException;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.domain.AbstractModel;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;
import com.erpnext.oa.act.dto.ResultListDataDTO;
import com.erpnext.oa.act.mapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {

	private final Logger log = LoggerFactory.getLogger(ModelServiceImpl.class);

	private static final String SORT_NAME_ASC = "nameAsc";
	private static final String SORT_NAME_DESC = "nameDesc";
	private static final String SORT_MODIFIED_ASC = "modifiedAsc";

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelImageService modelImageService;

	private BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();

	private BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();

	@Override
	public Model getModel(String modelId) {
		return modelMapper.selectByPrimaryKey(modelId);
	}

	@Override
	public ResultListDataDTO getModel(String filter, String sort, Integer modelType, HttpServletRequest request) {
		String filterText = request.getParameter("filterText");
		List<ModelDTO> resultList = new ArrayList<ModelDTO>();
		List<Model> models = modelMapper.selectModelList(AuthenticationUtils.getUserId(), modelType, filterText,
				getSort(sort, false));
		if (models != null) {
			models.forEach(model -> {
				resultList.add(new ModelDTO(model));
			});
		}

		return new ResultListDataDTO(resultList);
	}

	@Override
	public List<ModelDTO> getModel(String appId, String filter,Integer modelType) {
		List<Model> models ;
		if(modelType == null) {
			models = modelMapper.selectModelList(null, AbstractModel.MODEL_TYPE_BPMN, filter, null);
		}else {
			models = modelMapper.selectModelList(null, modelType, filter, null);
		}
		List<ModelDTO> list = new ArrayList<>();
		Optional.ofNullable(models).get().forEach(model -> {
			list.add(new ModelDTO(model));
		});
		return list;
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
		persistModel(newModel);
		return newModel;
	}

	@Override
	@Transactional
	public Model saveModel(Model modelObject) {
		return persistModel(modelObject);
	}

	@Override
	@Transactional
	public Model saveModel(Model modelObject, String editorJson, byte[] imageBytes, boolean newVersion,
			String newVersionComment, UserDetails updatedBy) {
		return internalSave(modelObject.getName(), modelObject.getModelKey(), modelObject.getDescription(), editorJson,
				newVersion, newVersionComment, imageBytes, updatedBy, modelObject);
	}

	@Override
	@Transactional
	public Model saveModel(String modelId, String name, String key, String description, String editorJson,
			boolean newVersion, String newVersionComment, UserDetails updatedBy) {
		Model modelObject = modelMapper.selectByPrimaryKey(modelId);
		return internalSave(name, key, description, editorJson, newVersion, newVersionComment, null, updatedBy,
				modelObject);
	}

	private Model internalSave(String name, String key, String description, String editorJson, boolean newVersion,
			String newVersionComment, byte[] imageBytes, UserDetails updatedBy, Model modelObject) {
		if (newVersion == false) {
			modelObject.setLastUpdated(new Date());
			modelObject.setLastUpdatedBy(updatedBy.getUsername());
			modelObject.setName(name);
			modelObject.setModelKey(key);
			modelObject.setDescription(description);
			modelObject.setModelEditorJson(editorJson);

			if (imageBytes != null) {
				modelObject.setThumbnail(imageBytes);
			}

		} else {
			// ModelHistory historyModel = createNewModelhistory(modelObject);
			// persistModelHistory(historyModel);
			// 记录历史
			modelObject.setVersion(modelObject.getVersion() + 1);
			modelObject.setLastUpdated(new Date());
			modelObject.setLastUpdatedBy(updatedBy.getUsername());
			modelObject.setName(name);
			modelObject.setModelKey(key);
			modelObject.setDescription(description);
			modelObject.setModelEditorJson(editorJson);
			modelObject.setModelComment(newVersionComment);
			if (imageBytes != null) {
				modelObject.setThumbnail(imageBytes);
			}
		}

		return persistModel(modelObject);
	}

	private Model persistModel(Model model) {
		if (modelMapper.selectByPrimaryKey(model.getId()) == null) {
			modelMapper.insert(model);
		}
		if (!StringUtils.isEmpty(model.getModelEditorJson())) {
			ObjectNode jsonNode = null;
			try {
				jsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
			} catch (IOException e) {
				log.warn("Could not deserialize json model");
			}
			System.out.println(model.getModelType());
			if ((model.getModelType() == null || model.getModelType().intValue() == Model.MODEL_TYPE_BPMN)) {
				modelImageService.generateThumbnailImage(model, jsonNode);
			} else if (model.getModelType().intValue() == Model.MODEL_TYPE_FORM
					|| model.getModelType().intValue() == Model.MODEL_TYPE_DECISION_TABLE) {
				jsonNode.put("name", model.getName());
				jsonNode.put("key", model.getModelKey());
			}
		}
		modelMapper.updateByPrimaryKey(model);
		return model;
	}

	@Override
	public BpmnModel getBpmnModel(AbstractModel model) {
		BpmnModel bpmnModel = null;
		try {
			/*Map<String, Model> formMap = new HashMap<String, Model>();
			Map<String, Model> decisionTableMap = new HashMap<String, Model>();

			List<Model> referencedModels = modelRepository.findModelsByParentModelId(model.getId());
			for (Model childModel : referencedModels) {
				if (Model.MODEL_TYPE_FORM == childModel.getModelType()) {
					formMap.put(childModel.getId(), childModel);

				} else if (Model.MODEL_TYPE_DECISION_TABLE == childModel.getModelType()) {
					decisionTableMap.put(childModel.getId(), childModel);
				}
			}*/

			bpmnModel = getBpmnModel(model, null, null);

		} catch (Exception e) {
			log.error("Could not generate BPMN 2.0 model for " + model.getId(), e);
			throw new InternalServerErrorException("Could not generate BPMN 2.0 model");
		}

		return bpmnModel;
	}

	@Override
	public BpmnModel getBpmnModel(AbstractModel model, Map<String, Model> formMap,
			Map<String, Model> decisionTableMap) {
		try {
			ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
			Map<String, String> formKeyMap = new HashMap<String, String>();
			if (formMap != null) {
				for (Model formModel : formMap.values()) {
					formKeyMap.put(formModel.getId(), formModel.getModelKey());
				}
			}
			Map<String, String> decisionTableKeyMap = new HashMap<String, String>();
			if (decisionTableMap != null) {
				for (Model decisionTableModel : decisionTableMap.values()) {
					decisionTableKeyMap.put(decisionTableModel.getId(), decisionTableModel.getModelKey());
				}
			}
			return bpmnJsonConverter.convertToBpmnModel(editorJsonNode, formKeyMap, decisionTableKeyMap);
		} catch (Exception e) {
			log.error("Could not generate BPMN 2.0 model for " + model.getId(), e);
			throw new InternalServerErrorException("Could not generate BPMN 2.0 model");
		}
	}
	
	@Override
	public byte[] getBpmnXML(BpmnModel bpmnModel) {
		for(Process process : bpmnModel.getProcesses()) {
			if(!StringUtils.isEmpty(process.getId())) {
				char firstCharacter = process.getId().charAt(0);
				if (Character.isDigit(firstCharacter)) {
					process.setId("a" + process.getId());
				}
			}
		}
		byte[] xmlBytes = bpmnXMLConverter.convertToXML(bpmnModel);
	    return xmlBytes;
	}

	private Sort getSort(String sort, boolean prefixWithProcessModel) {
		String propName;
		Direction direction;
		if (SORT_NAME_ASC.equals(sort)) {
			if (prefixWithProcessModel) {
				propName = "model.name";
			} else {
				propName = "name";
			}
			direction = Direction.ASC;
		} else if (SORT_NAME_DESC.equals(sort)) {
			if (prefixWithProcessModel) {
				propName = "model.name";
			} else {
				propName = "name";
			}
			direction = Direction.DESC;
		} else if (SORT_MODIFIED_ASC.equals(sort)) {
			if (prefixWithProcessModel) {
				propName = "model.lastUpdated";
			} else {
				propName = "lastUpdated";
			}
			direction = Direction.ASC;
		} else {
			// Default sorting
			if (prefixWithProcessModel) {
				propName = "model.lastUpdated";
			} else {
				propName = "lastUpdated";
			}
			direction = Direction.DESC;
		}
		return new Sort(direction, propName);
	}

	

}
