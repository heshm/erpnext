package com.erpnext.oa.act.endpoint;

import org.flowable.form.model.FormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.oa.act.service.ActFormService;


@RestController
@RequestMapping(value = "/oa/act/form")
public class FormEndpoint extends BaseEndpoint {
	
	@Autowired
	private ActFormService actFormService;
	
	@GetMapping("/start-form/{processDefinitionId}")
	public FormModel startForm(@PathVariable String processDefinitionId) {
		return actFormService.getProcessDefinitionStartForm(processDefinitionId);
	}

}
