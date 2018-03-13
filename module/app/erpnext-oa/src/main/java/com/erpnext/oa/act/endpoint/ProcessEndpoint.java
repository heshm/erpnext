package com.erpnext.oa.act.endpoint;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.oa.act.dto.ProcessQueryDTO;
import com.erpnext.oa.act.service.ProcessService;


@RestController
@RequestMapping(value = "/oa/act/process")
public class ProcessEndpoint extends BaseEndpoint {
	
	@Autowired
	private ProcessService processService;
	
	@GetMapping("/page")
	public Page<ProcessQueryDTO> page(
			@RequestParam("category")String category,
			@PageableDefault(size=10, page=0)Pageable pageable){
		return processService.getPageProcessDefinitionList(pageable,category);
	}
	
	@DeleteMapping("/delete/{deploymentId}")
	public String delete(@PathVariable String deploymentId) {
		processService.deleteDeployment(deploymentId);
		return DELETED;
	}
	
	@GetMapping("/running/page")
	public Page<ProcessInstance> pageRunning(
			@PageableDefault(size=10, page=0)Pageable pageable){
		return processService.getRunningInstance(pageable);
	}

}
