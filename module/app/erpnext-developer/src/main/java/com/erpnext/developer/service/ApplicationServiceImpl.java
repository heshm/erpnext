package com.erpnext.developer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Menu;
import com.erpnext.common.param.domain.MenuXref;
import com.erpnext.common.param.mapper.MenuMapper;
import com.erpnext.common.param.mapper.MenuXrefMapper;
import com.erpnext.developer.dto.ApplicationDTO;
import com.erpnext.framework.web.util.WebConst;

@Service
@Transactional(readOnly=true) 
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private MenuXrefMapper menuXrefMapper;
	
	@Override
	public ApplicationDTO readOneApplication(String id) {
		Menu menu = menuMapper.selectByPrimaryKey(id);
		return new ApplicationDTO(
				menu.getMenuId(),
				menu.getMenuName(),
				menu.getIconCls(),
				menu.getSequence(),
				menu.getPerm()
				);
	}

	@Override
	public List<ApplicationDTO> readAllApplication() {
		List<MenuXref> list = menuXrefMapper.selectList(WebConst.ROOT, null);
		List<ApplicationDTO> result = new ArrayList<>(list.size());
		
		list.forEach(item -> {
			result.add(readOneApplication(item.getChildMenuId()));
		});
		Collections.sort(result);
		return result;
	}

	

}
