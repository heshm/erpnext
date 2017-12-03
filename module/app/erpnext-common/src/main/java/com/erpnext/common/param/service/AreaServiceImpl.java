package com.erpnext.common.param.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.common.param.domain.Area;
import com.erpnext.common.param.dto.AreaDTO;
import com.erpnext.common.param.manager.AreaManager;
import com.erpnext.common.param.manager.DictManager;
import com.erpnext.common.param.mapper.AreaMapper;
import com.erpnext.common.util.CommonConst;
import com.erpnext.framework.web.util.WebConst;

@Service
@Transactional(readOnly=true) 
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaManager areaManager;
	
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private DictManager dictManager;
	
	@Override
	public AreaDTO getOneArea(String id){
		Area area = areaManager.getOneArea(id);
		if(area == null){ return null; }
		AreaDTO result = new AreaDTO(area);
		result.setTypeName(dictManager.readOneDict(CommonConst.DICT_AREA, area.getType()).getDictLabel());
		return result;
	}

	@Override
	public AreaDTO getNestedArea(String id){
		if(StringUtils.isEmpty(id)){
			id = WebConst.ROOT;
		}
		Area area = areaManager.getOneArea(id);
		if(area == null){ return null; }
		AreaDTO result = new AreaDTO(area);
		result.setTypeName(dictManager.readOneDict(CommonConst.DICT_AREA, area.getType()).getDictLabel());
		List<Area> childList = areaMapper.selectChild(id);
		if(childList != null){
			for(Area child : childList){
				result.getChildren().add(getNestedArea(child.getId()));
			}
		}
		return result;
	}

	@Override
	public List<AreaDTO> getSortedAreaList(String id) {
		List<AreaDTO> list = new ArrayList<>(1000);
		Area area = areaMapper.selectByPrimaryKey(id);
		if(area != null){
			AreaDTO areaDTO = new AreaDTO(area);
			areaDTO.setTypeName(dictManager.readOneDict(CommonConst.DICT_AREA, area.getType()).getDictLabel());
			list.add(areaDTO);
			List<Area> children = areaMapper.selectChild(id);
			if(children != null){
				for(Area cArea : children){
					list.addAll(getSortedAreaList(cArea.getId()));
				}
			}
		}
		return list;
	}

	@Override @Transactional
	public void delete(String id) {
		Area area = areaMapper.selectByPrimaryKey(id);
		if(area != null && (!area.getDelFlg().booleanValue())){
			area.setDelFlg(true);
			List<Area> childList = areaMapper.selectChild(id);
			if(childList != null){
				for(Area childArea : childList){
					childArea.setDelFlg(true);
					areaMapper.updateByPrimaryKey(childArea);
				}
			}
			areaMapper.updateByPrimaryKey(area);
		}
	}

}
