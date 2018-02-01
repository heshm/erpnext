package com.erpnext.oa.act.mapper;

import com.erpnext.oa.act.domain.Model;
import java.util.List;

public interface ModelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Model record);

    Model selectByPrimaryKey(String id);
    
    Model selectByModelKey(String modelKey);

    List<Model> selectAll();

    int updateByPrimaryKey(Model record);
}