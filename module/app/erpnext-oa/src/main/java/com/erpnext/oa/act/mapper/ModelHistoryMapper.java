package com.erpnext.oa.act.mapper;

import com.erpnext.oa.act.domain.ModelHistory;
import java.util.List;

public interface ModelHistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ModelHistory record);

    ModelHistory selectByPrimaryKey(String id);

    List<ModelHistory> selectAll();
    
    List<ModelHistory> selectByModelId(String modelId);

    int updateByPrimaryKey(ModelHistory record);
}