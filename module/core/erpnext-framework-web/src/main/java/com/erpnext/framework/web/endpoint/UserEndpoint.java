package com.erpnext.framework.web.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.security.domain.AdminUser;
import com.erpnext.framework.security.service.SiteSecurityService;

@RestController
public class UserEndpoint {
	
	@Autowired
	private SiteSecurityService siteSecurityService;
	
	@GetMapping("/getAllUser")
	//@PreAuthorize("hasAuthority('PERM_SYS_IMPORT')")
	@PreAuthorize("#oauth2.clientHasRole('ROLE_ADMIN')")
	public List<AdminUser> getAllUser() {
		System.out.println("Rest is called");
		return siteSecurityService.getAllUser();
	}

}
