package com.erpnext.common.param.service;

import java.util.List;

import com.erpnext.common.param.dto.AreaDTO;
import com.erpnext.common.param.dto.AreaSelectDTO;

public interface AreaService {
	/**
	 * 根据ID取得单一地区信息
	 * @param id
	 * @return
	 */
	AreaDTO getOneArea(String id);
	/**
	 * 根据ID取得树形结构的地区信息
	 * @param id
	 * @return
	 */
	AreaDTO getNestedArea(String id);
	/**
	 * 根据ID取得列表形式的地区信息
	 * @param id
	 * @return
	 */
	List<AreaDTO> getSortedAreaList(String id);
	/**
	 * 根据ID删除相应的区域信息
	 * @param id
	 */
	void delete(String id);
	
	void create(AreaDTO areaDTO);
	
	void update(AreaDTO areaDTO);
	
	List<AreaSelectDTO> getSelectedArea(String id);

}
