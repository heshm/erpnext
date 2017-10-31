package com.erpnext.stock.param.mapper;

import java.util.List;

import com.erpnext.stock.param.domain.ItemGroup;

public interface ItemGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemGroup record);

    ItemGroup selectByPrimaryKey(String id);

    List<ItemGroup> selectAll();

    int updateByPrimaryKey(ItemGroup record);
    
    List<ItemGroup> selectChildren(String pId);
    
    int updateBySelective(ItemGroup record);
}