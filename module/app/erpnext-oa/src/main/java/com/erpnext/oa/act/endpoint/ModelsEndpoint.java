package com.erpnext.oa.act.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelDTO;
import com.erpnext.oa.act.service.ModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/oa/act/models")
public class ModelsEndpoint extends BaseEndpoint {

	@Autowired
	private ModelService modelService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/list")
	public List<ModelDTO> getModel(
			@RequestParam(name="appId",required = false) String appId,
			@RequestParam(name="name",required = false) String name){
		return modelService.getModel(appId, name);
	}

	@PostMapping("/create")
	public ModelDTO createModel(@RequestBody ModelDTO modelDTO) throws Exception {
		boolean keyAlreadyExists = modelService.validateModelKey(modelDTO.getKey());
		if (keyAlreadyExists) {
			throw new RuntimeException("Provided model key already exists: " + modelDTO.getKey());
		}
		String json = null;

		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.set("stencilset", stencilSetNode);
		ObjectNode propertiesNode = objectMapper.createObjectNode();
		propertiesNode.put("process_id", modelDTO.getKey());
		propertiesNode.put("name", modelDTO.getName());
		if (!StringUtils.isEmpty(modelDTO.getDescription())) {
			propertiesNode.put("documentation", modelDTO.getDescription());
		}
		editorNode.set("properties", propertiesNode);

		ArrayNode childShapeArray = objectMapper.createArrayNode();
		editorNode.set("childShapes", childShapeArray);
		ObjectNode childNode = objectMapper.createObjectNode();
		childShapeArray.add(childNode);
		ObjectNode boundsNode = objectMapper.createObjectNode();
		childNode.set("bounds", boundsNode);
		ObjectNode lowerRightNode = objectMapper.createObjectNode();
		boundsNode.set("lowerRight", lowerRightNode);
		lowerRightNode.put("x", 130);
		lowerRightNode.put("y", 193);
		ObjectNode upperLeftNode = objectMapper.createObjectNode();
		boundsNode.set("upperLeft", upperLeftNode);
		upperLeftNode.put("x", 100);
		upperLeftNode.put("y", 163);
		childNode.set("childShapes", objectMapper.createArrayNode());
		childNode.set("dockers", objectMapper.createArrayNode());
		childNode.set("outgoing", objectMapper.createArrayNode());
		childNode.put("resourceId", "startEvent1");
		ObjectNode stencilNode = objectMapper.createObjectNode();
		childNode.set("stencil", stencilNode);
		stencilNode.put("id", "StartNoneEvent");
		json = editorNode.toString();
		Model newModel = modelService.createModel(modelDTO, json);
		return new ModelDTO(newModel);
	}

}
