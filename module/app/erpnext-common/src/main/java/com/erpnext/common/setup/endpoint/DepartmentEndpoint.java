package com.erpnext.common.setup.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.setup.dto.DepartmentDTO;
import com.erpnext.common.setup.service.DepartmentService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/setup/department")
public class DepartmentEndpoint extends BaseEndpoint {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/listOne/{id}")
	public DepartmentDTO listOne(@PathVariable("id")String id) {
		return departmentService.getOneDepartment(id);
	}
	@GetMapping("/treeList/{id}")
	public DepartmentDTO treeList(@PathVariable("id")String id) {
		return departmentService.getNestedDepartment(id);
	}

}
