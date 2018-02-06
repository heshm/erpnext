package com.erpnext.oa.act.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.controller.BaseController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/oa/act/app")
public class StencilSetController extends BaseController{
	
	private static final String FILE_NAME = "stencilset_bpmn";
	private static final String FILE_EXTENSION = ".json";

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/rest/stencil-sets/editor", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public JsonNode getStencilSetForEditor() throws IOException {
		
		Locale locale = LocaleContextHolder.getLocale();
		String resourceName = FILE_NAME + "_" + locale.toString() + FILE_EXTENSION;
		InputStream stencilsetStream = null;
		stencilsetStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if(null == stencilsetStream){
			stencilsetStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME + FILE_EXTENSION);
		}
		//System.out.println(IOUtils.toString(stencilsetStream,"utf-8"));
		//return IOUtils.toString(stencilsetStream,"utf-8");
		JsonNode stencilNode = objectMapper.readTree(stencilsetStream);
		return stencilNode;

	}
}