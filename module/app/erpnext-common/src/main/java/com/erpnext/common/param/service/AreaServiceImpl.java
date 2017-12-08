package com.erpnext.common.param.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.common.param.domain.Area;
import com.erpnext.common.param.dto.AreaDTO;
import com.erpnext.common.param.dto.AreaSelectDTO;
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
					delete(childArea.getId());
				}
			}
			areaMapper.updateByPrimaryKey(area);
		}
	}
	
	@Override
	@Transactional
	public void create(AreaDTO areaDTO){
		Area area = areaMapper.selectByPrimaryKey(areaDTO.getId());
		if(area != null) {
			if(!area.getDelFlg()) {
				throw new IllegalArgumentException("该区域代码已经存在!");
			}
		}
		area = areaMapper.selectByPrimaryKey(areaDTO.getParentId());
		if(area.getIsLeaf()) {
			area.setIsLeaf(false);
			areaMapper.updateByPrimaryKey(area);
		}
		areaDTO.setDelFlg(false);
		areaDTO.setSort((short)0);
		areaDTO.setIsLeaf(true);
		areaMapper.insert(areaDTO);
		
	}
	
	@Override
	@Transactional
	public void update(AreaDTO area){
		area.setDelFlg(false);
		area.setSort((short)0);
		areaMapper.updateByPrimaryKey(area);
	}

	@Override
	@Cacheable(cacheNames="paramCache")
	public List<AreaSelectDTO> getSelectedArea(String id) {
		List<Area> list = areaMapper.selectChild(id);
		List<AreaSelectDTO> result = new LinkedList<>();
		for(Area area: list) {
			AreaSelectDTO dto = new AreaSelectDTO();
			dto.setValue(area.getId());
			dto.setLabel(area.getName());
			dto.setChildren(getSelectedArea(area.getId()));
			result.add(dto);
		}
		return result;
	}

	@Override
	public AreaDTO getOneNestedArea(String id) {
		Area area = areaManager.getOneArea(id);
		if(area == null){ return null; }
		AreaDTO result = new AreaDTO(area);
		result.setTypeName(dictManager.readOneDict(CommonConst.DICT_AREA, area.getType()).getDictLabel());
		List<Area> childList = areaMapper.selectChild(id);
		if(childList != null && childList.size() > 0){
			for(Area child : childList){
				AreaDTO childDto = new AreaDTO(child);
				childDto.setTypeName(dictManager.readOneDict(CommonConst.DICT_AREA, area.getType()).getDictLabel());
				result.getChildren().add(childDto);
			}
		}
		return result;
	}

	@Override
	public List<AreaSelectDTO> getSelectedAreaWithoutChildren(String id) {
		List<Area> list = areaMapper.selectChild(id);
		List<AreaSelectDTO> result = new LinkedList<>();
		for(Area area: list) {
			AreaSelectDTO dto = new AreaSelectDTO();
			dto.setValue(area.getId());
			dto.setLabel(area.getName());
			result.add(dto);
		}
		return result;
	}

}
