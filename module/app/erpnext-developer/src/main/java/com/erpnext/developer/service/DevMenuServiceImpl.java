package com.erpnext.developer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Menu;
import com.erpnext.common.param.domain.MenuXref;
import com.erpnext.common.param.dto.MenuDTO;
import com.erpnext.common.param.mapper.MenuMapper;
import com.erpnext.common.param.mapper.MenuXrefMapper;
import com.erpnext.developer.service.DevMenuService;
import com.erpnext.framework.web.util.WebConst;


@Service
@Transactional(readOnly=true) 
public class DevMenuServiceImpl implements DevMenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private MenuXrefMapper menuXrefMapper;

	@Override
	public List<MenuDTO> readApplicationMenu(String appId) {
		return readMenuWithChildren(appId).getChildren();
	}
	
	private MenuDTO readMenuWithChildren(String menuId){
		Menu menu = menuMapper.selectByMenuId(menuId);
		Menu pMenu = null;
		if(!WebConst.ROOT.equals(menuId)){
			MenuXref menuXref = menuXrefMapper.selectOne(menuId);
			pMenu = menuMapper.selectByPrimaryKey(menuXref.getMenuId());
		}
		MenuDTO menuDTO = new MenuDTO(menu,pMenu);
		if(menu != null && !menu.getIsLeaf()){
			List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
			for(Menu childMenu : menu.getChildren()){
				menuDTOList.add(readMenuWithChildren(childMenu.getMenuId()));
			}
			menuDTO.setChildren(menuDTOList);
		}
		return menuDTO;
				
	}

}
