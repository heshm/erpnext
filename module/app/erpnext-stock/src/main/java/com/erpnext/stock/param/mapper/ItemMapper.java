package com.erpnext.stock.param.mapper;

import com.erpnext.stock.param.domain.Item;
import java.util.List;
import java.util.Map;

public interface ItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(Item record);

    Item selectByPrimaryKey(String itemId);

    List<Item> selectAll();
    
    List<Item> selectList(Map<String,Object> param);

    int updateByPrimaryKey(Item record);
    
    int updateByPrimaryKeySelective(Item record);
}