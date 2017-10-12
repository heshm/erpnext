package com.erpnext.framework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {
	
	private final static String loginView = "login";
	
	@GetMapping("/login")
	public String login(){
		return loginView;
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(){
		//loger.info("{} login success","admin");
		return "redirect:/index";
	}

}
