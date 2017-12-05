package com.erpnext.common.setup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.authority.manager.UserManager;
import com.erpnext.common.param.manager.AreaManager;
import com.erpnext.common.param.manager.DictManager;
import com.erpnext.common.setup.domain.Department;
import com.erpnext.common.setup.dto.DepartmentDTO;
import com.erpnext.common.setup.mapper.DepartmentMapper;
import com.erpnext.common.util.CommonConst;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private DictManager dictManager;
	@Autowired
	private UserManager userManager;

	@Override
	public DepartmentDTO getOneDepartment(String id) {
		Department department = departmentMapper.selectByPrimaryKey(id);
		if(department == null) return null;
		String areaName = areaManager.getFullAreaName(department.getAreaCode());
		DepartmentDTO departmentDTO = new DepartmentDTO(department, areaName);
		departmentDTO.setTypeName(
				dictManager.readOneDict(CommonConst.DICT_DEPARTMENT, departmentDTO.getType()).getDictLabel());
		departmentDTO.setPrimaryPersonName(userManager.getOneUser(departmentDTO.getPrimaryPerson()).getUserName());
		return departmentDTO;
	}

	@Override
	public DepartmentDTO getNestedDepartment(String id) {
		Department department = departmentMapper.selectByPrimaryKey(id);
		if (department == null) return null;
		
		String areaName = areaManager.getFullAreaName(department.getAreaCode());
		DepartmentDTO departmentDTO = new DepartmentDTO(department, areaName);
		departmentDTO.setTypeName(
				dictManager.readOneDict(CommonConst.DICT_DEPARTMENT, departmentDTO.getType()).getDictLabel());
		departmentDTO.setPrimaryPersonName(userManager.getOneUser(departmentDTO.getPrimaryPerson()).getUserName());
		List<Department> departList = departmentMapper.selectChild(id);
		if (departList != null) {
			for (Department depart : departList) {
				departmentDTO.getChildren().add(getNestedDepartment(depart.getId()));
			}
		}
		return departmentDTO;
	}
	

}
