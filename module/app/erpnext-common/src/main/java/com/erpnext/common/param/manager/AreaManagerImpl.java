package com.erpnext.common.param.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Area;
import com.erpnext.common.param.mapper.AreaMapper;
import com.erpnext.common.util.CommonConst;

@Component
@Transactional(readOnly=true) 
public class AreaManagerImpl implements AreaManager {
	
	@Autowired
	private AreaMapper aeraMapper;

	@Override
	public Area getOneArea(String id) {
		Area area = aeraMapper.selectByPrimaryKey(id);
		if(area == null)
			return null;
		return area;
	}

	@Override
	public String getFullAreaName(String code) {
		Area area = aeraMapper.selectAreaByCode(code);
		if(area == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Stack<Area> stack = new Stack<>();
		stack.add(area);
		while(!area.getType().equals(CommonConst.PROVINCE_AREA_TYPE)){
			area = aeraMapper.selectByPrimaryKey(area.getParentId());
			stack.push(area);
		}
		while(!stack.isEmpty()){
			sb.append(stack.pop().getName());
		}
		return sb.toString();
	}
	@Override
	public String getFullAreaName(String[] codes){
		StringBuilder sb = new StringBuilder();
		for(String code: codes){
			sb.append(aeraMapper.selectAreaNameByCode(code));
		}
		return sb.toString();
	}
	
	@Override
	public List<String> getChildCodeList(String code) {
		Area area = aeraMapper.selectAreaByCode(code);
		if(area == null)   return null;
		List<String> resultList = new LinkedList<>();
		resultList.add(area.getPostalCode());
		List<Area> children = aeraMapper.selectChild(area.getId());
		for(Area child : children){
			resultList.addAll(getChildCodeList(child.getPostalCode()));
		}
		return resultList;
	}

}
