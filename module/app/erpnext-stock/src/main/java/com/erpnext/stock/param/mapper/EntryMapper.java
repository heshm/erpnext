package com.erpnext.stock.param.mapper;

import com.erpnext.stock.param.domain.Entry;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EntryMapper {
    int deleteByPrimaryKey(@Param("warehouseId") String warehouseId, @Param("id") String id);

    int insert(Entry record);

    Entry selectByPrimaryKey(@Param("warehouseId") String warehouseId, @Param("id") String id);

    List<Entry> selectAll();

    int updateByPrimaryKey(Entry record);

    List<Entry> selectByParent(String warehouseId);
}