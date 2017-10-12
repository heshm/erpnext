package com.erpnext.framework.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.erpnext.framework.security.service.SiteSecurityService;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private SiteSecurityService siteSecurityService;
	
	@GetMapping("/index")
	public String index(){
		siteSecurityService.readAdminUserById("admin");
		return "index";
	}

}
