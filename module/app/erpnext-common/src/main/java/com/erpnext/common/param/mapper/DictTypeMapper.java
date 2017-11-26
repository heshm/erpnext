package com.erpnext.common.param.mapper;

import com.erpnext.common.param.domain.DictType;
import java.util.List;

public interface DictTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(DictType record);

    DictType selectByPrimaryKey(String id);

    List<DictType> selectAll();

    int updateByPrimaryKey(DictType record);
}