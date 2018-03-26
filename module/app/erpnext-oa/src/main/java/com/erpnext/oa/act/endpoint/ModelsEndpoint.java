package com.erpnext.oa.act.endpoint;

import java.util.List;

import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.dto.ModelRepresentation;
import com.erpnext.oa.act.service.DeploymentService;
import com.erpnext.oa.act.service.ModelService;

@RestController
@RequestMapping(value = "/oa/act/models")
public class ModelsEndpoint extends BaseEndpoint {

	@Autowired
	private ModelService modelService;
	@Autowired
	private DeploymentService deploymentService;
	
	@GetMapping("/list")
	public List<ModelRepresentation> getModel(
			@RequestParam(name="appId",required = false) String appId,
			@RequestParam(name="name",required = false) String name,
			@RequestParam(name="modelType",required = false) Integer modelType){
		return modelService.getModel(appId, name, modelType);
	}
	
	@PostMapping("/create")
    public ModelRepresentation createModel(@RequestBody ModelRepresentation modelRepresentation) {
        modelRepresentation.setKey(modelRepresentation.getKey().replaceAll(" ", ""));
        String json = modelService.createModelJson(modelRepresentation);

        Model newModel = modelService.createModel(modelRepresentation, json);
        return new ModelRepresentation(newModel);
    }

	
	
	@PutMapping("/deploy/{modelId}")
	public Deployment deploy(@PathVariable("modelId") String modelId) {
		return deploymentService.deployModel(modelId);
	}
	
	@PutMapping("/category/{modelId}/{appId}")
	public String category(@PathVariable("modelId") String modelId,@PathVariable("appId") String appId) {
		modelService.updateCategory(modelId, appId);
		return UPDATED;
	}

}
