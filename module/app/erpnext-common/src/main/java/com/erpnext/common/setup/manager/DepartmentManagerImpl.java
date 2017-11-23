package com.erpnext.common.setup.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.setup.domain.Department;
import com.erpnext.common.setup.mapper.DepartmentMapper;

@Component
@Transactional(readOnly=true) 
public class DepartmentManagerImpl implements DepartmentManager {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public Department getOneDepartment(String id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

}
