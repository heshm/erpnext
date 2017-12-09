package com.erpnext.common.authority.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.authority.dto.AdminUserDTO;
import com.erpnext.common.authority.mapper.AdminUserDTOMapper;
import com.erpnext.common.setup.domain.Department;

@Service
@Transactional(readOnly=true) 
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private AdminUserDTOMapper adminUserDTOMapper;

	@Override
	public AdminUserDTO getOneAdminUser(String id) {
		AdminUserDTO adminUser = adminUserDTOMapper.selectOne(id);
		if(adminUser == null) 
			return new AdminUserDTO();
		if(adminUser.getDepartments() != null){
			StringBuilder ids = new StringBuilder();
			StringBuilder names = new StringBuilder();
			List<Department> departList = adminUser.getDepartments();
			for(int i = 0;i < departList.size(); i++){
				ids.append(departList.get(i).getId());
				names.append(departList.get(i).getName());
				if(i != (departList.size() - 1)){
					ids.append(",");
					names.append(",");
				}
			}
			adminUser.setDepartmentIds(ids.toString());
			adminUser.setDepartmentNames(names.toString());
		}
		return adminUser;
	}

	@Override
	public Page<AdminUserDTO> getPageAdminUser(Pageable pageable, Map<String, Object> param) {
		param.put("pageable", pageable);
		List<AdminUserDTO> list = adminUserDTOMapper.selectList(param);
		int total = adminUserDTOMapper.countByExample(param);
		return new PageImpl<>(list,pageable,total);
	}


}
