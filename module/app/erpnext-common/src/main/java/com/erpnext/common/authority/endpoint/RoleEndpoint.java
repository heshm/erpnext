package com.erpnext.common.authority.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.authority.service.RoleService;
import com.erpnext.framework.domain.AdminRole;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/authority/role")
public class RoleEndpoint extends BaseEndpoint {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public List<AdminRole> list(){
		return roleService.getAllRole();
	}
	
	@PostMapping("/create")
	public String create(@RequestBody AdminRole role) {
		roleService.saveRole(role);
		return CREATED;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		roleService.deleteRole(id);
		return DELETED;
	}

}
