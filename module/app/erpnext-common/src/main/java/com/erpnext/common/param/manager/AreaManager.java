package com.erpnext.common.param.manager;

import java.util.List;

import com.erpnext.common.param.domain.Area;

public interface AreaManager {
	
	Area getOneArea(String id);
	/**
	 * @param 行政编码
	 * @return 完整的地区名
	 */
	String getFullAreaName(String code);
	
	List<String> getChildCodeList(String code);

}
