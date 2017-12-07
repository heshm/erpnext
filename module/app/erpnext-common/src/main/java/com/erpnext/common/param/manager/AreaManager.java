package com.erpnext.common.param.manager;


import com.erpnext.common.param.domain.Area;

public interface AreaManager {
	
	Area getOneArea(String id);
	
	String getFullAreaName(String[] codes);

}
