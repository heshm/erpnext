package com.erpnext.oa.act.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.controller.BaseController;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;
import com.erpnext.oa.act.dto.ResultListDataDTO;
import com.erpnext.oa.act.service.ModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/oa/act/app")
public class ModelController extends BaseController {

	private static final String RESOLVE_ACTION_OVERWRITE = "overwrite";
	private static final String RESOLVE_ACTION_SAVE_AS = "saveAs";
	private static final String RESOLVE_ACTION_NEW_VERSION = "newVersion";

	@Autowired
	private ModelService modelService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/rest/models")
	public ResultListDataDTO getModels(@RequestParam(required = false) String filter, @RequestParam(required = false) String sort, @RequestParam(required = false) Integer modelType,
		      HttpServletRequest request) {
		return modelService.getModel(filter, sort, modelType, request);
	}

    @PostMapping("/rest/models/{modelId}/editor/json")
	public ModelDTO saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values)
			throws Exception {
		long lastUpdated = -1L;
		String lastUpdatedString = values.getFirst("lastUpdated");
		if (lastUpdatedString == null) {
			throw new RuntimeException("Missing lastUpdated date");
		}
		Date readValue = objectMapper.getDeserializationConfig().getDateFormat().parse(lastUpdatedString);
		lastUpdated = readValue.getTime();

		Model model = modelService.getModel(modelId);
		UserDetails currentUser = AuthenticationUtils.getPrincipal();
		boolean currentUserIsOwner = model.getLastUpdatedBy().equals(currentUser.getUsername());
		String resolveAction = values.getFirst("conflictResolveAction");

		if (model.getLastUpdated().getTime() != lastUpdated) {
			if (RESOLVE_ACTION_SAVE_AS.equals(resolveAction)) {
				String saveAs = values.getFirst("saveAs");
				String json = values.getFirst("json_xml");
				return createNewModel(saveAs, model.getDescription(), model.getModelType(), json);
			}else if (RESOLVE_ACTION_OVERWRITE.equals(resolveAction)) {
				return updateModel(model, values, false);
			}else if (RESOLVE_ACTION_NEW_VERSION.equals(resolveAction)) {
				return updateModel(model, values, true);
			}else {
				String isNewVersionString = values.getFirst("newversion");
				if (currentUserIsOwner && "true".equals(isNewVersionString)) {
					return updateModel(model, values, true);
				}else {
					throw new RuntimeException("Process model was updated in the meantime");
				}
			}
		}else {
		      return updateModel(model, values, false);
		}
	}
    
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
		if (StringUtils.isEmpty(model.getModelEditorJson())) {
			ObjectNode editorJsonNode = objectMapper.createObjectNode();
			editorJsonNode.put("id", "canvas");
			editorJsonNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorJsonNode.put("modelType", "model");
			modelNode.set("model", editorJsonNode);
		} else {
			ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
			editorJsonNode.put("modelType", "model");
			modelNode.set("model", editorJsonNode);
		}
		return modelNode;
	}

	private ModelDTO updateModel(Model model, MultiValueMap<String, String> values,
			boolean forceNewVersion) {

		String name = values.getFirst("name");
		String key = values.getFirst("key");
		String description = values.getFirst("description");
		String isNewVersionString = values.getFirst("newversion");
		String newVersionComment = null;

		boolean newVersion = false;
		if (forceNewVersion) {
			newVersion = true;
			newVersionComment = values.getFirst("comment");
		} else {
			if (isNewVersionString != null) {
				newVersion = "true".equals(isNewVersionString);
				newVersionComment = values.getFirst("comment");
			}
		}
		String json = values.getFirst("json_xml");
		model = modelService.saveModel(model.getId(), name, key, description, json, newVersion, newVersionComment,
				AuthenticationUtils.getPrincipal());
		return new ModelDTO(model);

		
	}

	private ModelDTO createNewModel(String name, String description, Integer modelType, String editorJson) {
		ModelDTO model = new ModelDTO();
		model.setName(name);
		model.setDescription(description);
		model.setModelType(modelType);
		modelService.createModel(model, editorJson);
		return model;
	}

}
