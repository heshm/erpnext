package com.erpnext.oa.act.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erpnext.oa.act.service.ProcessService;

@Controller
@RequestMapping(value = "/oa/act/process")
public class ProcessControler {
	@Autowired
	private ProcessService processService;
	
	@GetMapping("/resource/{processDefinitionId}/{resourceType}")
	public void readResource(
			@PathVariable("processDefinitionId") String processDefinitionId,
			@PathVariable("resourceType") String resourceType,HttpServletResponse response){
		processService.getResource(processDefinitionId, resourceType, response);
	}

}
