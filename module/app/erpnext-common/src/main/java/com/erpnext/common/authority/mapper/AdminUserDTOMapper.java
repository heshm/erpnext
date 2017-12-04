package com.erpnext.common.authority.mapper;

import java.util.List;
import java.util.Map;

import com.erpnext.common.authority.dto.AdminUserDTO;

public interface AdminUserDTOMapper {
	
	AdminUserDTO selectOne(String userId);
	
	List<AdminUserDTO> selectList(Map<String,Object> map);
	
	int countByExample(Map<String,Object> map);

}
