package com.erpnext.stock.param.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.framework.manager.SequenceManager;
import com.erpnext.stock.param.domain.Item;
import com.erpnext.stock.param.dto.ItemDTO;
import com.erpnext.stock.param.mapper.ItemGroupMapper;
import com.erpnext.stock.param.mapper.ItemMapper;

@Service
@Transactional(readOnly=true)
public class ItemServiceImpl implements ItemService {
	
	private static final String ID_PREFIX = "ITEM-";
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemGroupMapper itemGroupMapper;
	@Autowired
	private SequenceManager sequenceManager;

	@Override
	public List<ItemDTO> readItemList(Map<String, Object> map) {
		List<Item> list = itemMapper.selectList(map);
		if(list != null){
			List<ItemDTO> dtoList = new ArrayList<>(list.size());
			for(Item item : list){
				dtoList.add(new ItemDTO(item));
			} 
			return dtoList;
		}else{
			return null;
		}
	}

	@Override
	public ItemDTO readItemById(String id) {
		Item item = itemMapper.selectByPrimaryKey(id);
		if(item == null) return null;
		ItemDTO dto = new ItemDTO();
		BeanUtils.copyProperties(item, dto);
		dto.setItemGroupName(itemGroupMapper.selectByPrimaryKey(dto.getItemGroupId()).getName());
		return dto;
	}

}
