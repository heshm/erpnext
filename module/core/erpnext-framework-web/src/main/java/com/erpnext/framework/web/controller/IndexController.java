package com.erpnext.framework.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erpnext.framework.security.domain.AdminUser;
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
	
	@GetMapping("/getAllUser")
	public @ResponseBody List<AdminUser> getAllUser(){
		return siteSecurityService.getAllUser();
	}

}
