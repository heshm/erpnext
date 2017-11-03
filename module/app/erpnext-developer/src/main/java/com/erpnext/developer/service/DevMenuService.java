package com.erpnext.developer.service;

import java.util.List;

import com.erpnext.common.param.dto.MenuDTO;


public interface DevMenuService {
	
	List<MenuDTO> readApplicationMenu(String appId);

}
