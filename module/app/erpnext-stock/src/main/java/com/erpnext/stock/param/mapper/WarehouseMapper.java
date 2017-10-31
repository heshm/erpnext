package com.erpnext.stock.param.mapper;

import java.util.List;

import com.erpnext.stock.param.domain.Warehouse;

public interface WarehouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(Warehouse record);

    Warehouse selectByPrimaryKey(String id);

    List<Warehouse> selectAll();

    int updateByPrimaryKey(Warehouse record);
}