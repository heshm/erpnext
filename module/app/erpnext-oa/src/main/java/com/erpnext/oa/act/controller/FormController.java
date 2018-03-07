package com.erpnext.oa.act.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.oa.act.dto.FormRepresentation;
import com.erpnext.oa.act.dto.FormSaveRepresentation;
import com.erpnext.oa.act.dto.ResultListDataRepresentation;
import com.erpnext.oa.act.service.ActFormService;

@RestController
@RequestMapping(value = "/oa/act/app/rest/form-models")
public class FormController {
	@Autowired
	private ActFormService actFormService;
	
	@GetMapping("/{formId}")
	public FormRepresentation getForm(@PathVariable String formId,HttpServletRequest request) {
		return actFormService.getForm(formId);
	}
	
	@PutMapping("/{formId}")
	public FormRepresentation saveForm(@PathVariable String formId, @RequestBody FormSaveRepresentation saveRepresentation) {
		return actFormService.saveForm(formId, saveRepresentation);
	}
	
	@GetMapping
	public ResultListDataRepresentation getForms(HttpServletRequest request) {
		String filter = request.getParameter("filter");
		return actFormService.getForms(filter);
	}

}
