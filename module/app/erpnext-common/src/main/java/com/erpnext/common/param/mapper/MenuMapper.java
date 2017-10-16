package com.erpnext.common.param.mapper;

import com.erpnext.common.param.domain.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(String menuId);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
    
    Menu selectByMenuId(String menuId);
}