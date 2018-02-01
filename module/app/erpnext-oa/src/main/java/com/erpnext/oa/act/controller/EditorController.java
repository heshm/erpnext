package com.erpnext.oa.act.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erpnext.framework.web.controller.BaseController;

@Controller
@RequestMapping(value = "/oa/act/editor")
public class EditorController extends BaseController {
	
	private static final String editorView = "oa/act/index";
	
	@GetMapping("/init")
	public String init() {
		return editorView;
	}

}
