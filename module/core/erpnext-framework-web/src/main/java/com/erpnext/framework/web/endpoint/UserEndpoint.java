package com.erpnext.framework.web.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.security.domain.AdminUser;
import com.erpnext.framework.security.service.SiteSecurityService;

@RestController
public class UserEndpoint extends BaseEndpoint{
	
	@Autowired
	private SiteSecurityService siteSecurityService;
	
	@GetMapping("/getAllUser")
	public List<AdminUser> getAllUser() {
		logger.debug("Rest controller {} is called.","UserEndpoint");
		return siteSecurityService.getAllUser();
	}

}
