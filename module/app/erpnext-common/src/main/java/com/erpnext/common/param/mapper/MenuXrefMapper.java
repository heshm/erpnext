package com.erpnext.common.param.mapper;

import com.erpnext.common.param.domain.MenuXref;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuXrefMapper {
    int deleteByPrimaryKey(@Param("menuId") String menuId, @Param("childMenuId") String childMenuId);

    int insert(MenuXref record);

    List<MenuXref> selectList(@Param("menuId") String menuId,@Param("childMenuId") String childMenuId);
    
    MenuXref selectOne(@Param("childMenuId") String childMenuId);
    
    int deleteByMenuId(@Param("menuId") String menuId,@Param("childMenuId") String childMenuId);
    
    List<MenuXref> selectListByCmenu(@Param("menuIdArray")String[] menuIdArray);
}