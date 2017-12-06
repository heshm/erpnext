package com.erpnext.common.setup.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

//import com.erpnext.common.authority.manager.UserManager;
import com.erpnext.common.param.manager.AreaManager;
import com.erpnext.common.param.manager.DictManager;
import com.erpnext.common.setup.domain.Department;
import com.erpnext.common.setup.dto.DepartmentDTO;
import com.erpnext.common.setup.mapper.DepartmentMapper;
import com.erpnext.common.util.CommonConst;
import com.erpnext.framework.util.IDUtils;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private DictManager dictManager;
	//@Autowired
	//private UserManager userManager;

	@Override
	public DepartmentDTO getOneDepartment(String id) {
		Department department = departmentMapper.selectByPrimaryKey(id);
		if(department == null) return null;
		String areaName = areaManager.getFullAreaName(department.getAreaCode().split(","));
		DepartmentDTO departmentDTO = new DepartmentDTO(department, areaName);
		departmentDTO.setTypeName(
				dictManager.readOneDict(CommonConst.DICT_DEPARTMENT, departmentDTO.getType()).getDictLabel());
		//departmentDTO.setPrimaryPersonName(userManager.getOneUser(departmentDTO.getPrimaryPerson()).getUserName());
		return departmentDTO;
	}

	@Override
	public DepartmentDTO getNestedDepartment(String id) {
		Department department = departmentMapper.selectByPrimaryKey(id);
		if (department == null) return null;
		
		String areaName = areaManager.getFullAreaName(department.getAreaCode().split(","));
		DepartmentDTO departmentDTO = new DepartmentDTO(department, areaName);
		departmentDTO.setTypeName(
				dictManager.readOneDict(CommonConst.DICT_DEPARTMENT, departmentDTO.getType()).getDictLabel());
		//departmentDTO.setPrimaryPersonName(userManager.getOneUser(departmentDTO.getPrimaryPerson()).getUserName());
		List<Department> departList = departmentMapper.selectChild(id);
		if (departList != null) {
			for (Department depart : departList) {
				departmentDTO.getChildren().add(getNestedDepartment(depart.getId()));
			}
		}
		return departmentDTO;
	}

	@Override
	@Transactional
	public String create(DepartmentDTO dept) {
		Department department = new Department();
		BeanUtils.copyProperties(dept, department);
		department.setId(IDUtils.uuid());
		department.setAreaCode(StringUtils.arrayToCommaDelimitedString(dept.getAreaCode()));
		department.setDelFlg(false);
		departmentMapper.insert(department);
		return department.getId();
	}
	
	@Override
	@Transactional
	public void create(DepartmentDTO dept,String[] params){
		String id = create(dept);
		for(String param: params){
			Department department = new Department();
			BeanUtils.copyProperties(dept, department);
			department.setId(IDUtils.uuid());
			department.setParentId(id);
			department.setAreaCode(StringUtils.arrayToCommaDelimitedString(dept.getAreaCode()));
			department.setType("2");
			switch(param){
			case "general" :
				department.setName("综合部");
				break;
			case "market" :
				department.setName("市场部");
				break;
			case "delevop" :
				department.setName("研发部");
				break;
			default :
				throw new IllegalArgumentException("不支持的快速添加类型!");
			}
			departmentMapper.insert(department);
		}
	}

	@Override
	@Transactional
	public void update(DepartmentDTO dept) {
		Department department = new Department();
		BeanUtils.copyProperties(dept, department);
		department.setAreaCode(StringUtils.arrayToCommaDelimitedString(dept.getAreaCode()));
		departmentMapper.updateByPrimaryKey(department);
	}
	

}
