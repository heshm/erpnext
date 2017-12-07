package com.erpnext.common.param.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Area;
import com.erpnext.common.param.mapper.AreaMapper;

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
	public String getFullAreaName(String[] codes){
		StringBuilder sb = new StringBuilder();
		for(String code: codes){
			sb.append(aeraMapper.selectAreaNameByCode(code));
		}
		return sb.toString();
	}
	


}
