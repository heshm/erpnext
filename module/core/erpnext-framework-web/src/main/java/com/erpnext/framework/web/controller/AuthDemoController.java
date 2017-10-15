package com.erpnext.framework.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthDemoController {

	@GetMapping("/authDemo")
	public String authDemo() {
		return "authDemo";
	}
	
}
