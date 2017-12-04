package com.erpnext.common.setup.service;

import com.erpnext.common.setup.dto.DepartmentDTO;

public interface DepartmentService {
	
	DepartmentDTO getOneDepartment(String id);
	
	DepartmentDTO getNestedDepartment(String id);
	
}
