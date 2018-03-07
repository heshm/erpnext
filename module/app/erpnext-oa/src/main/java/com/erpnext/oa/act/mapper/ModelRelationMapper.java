package com.erpnext.oa.act.mapper;

import com.erpnext.oa.act.domain.ModelRelation;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ModelRelationMapper {
    int deleteByPrimaryKey(String id);
    
    int deleteByParentModelIdAndType(@Param("parentModelId")String parentModelId,@Param("relationType")String relationType);

    int insert(ModelRelation record);

    ModelRelation selectByPrimaryKey(String id);

    List<ModelRelation> selectAll();
    
    List<ModelRelation> selectByParentModelIdAndType(@Param("parentModelId")String parentModelId,@Param("relationType")String relationType);

    int updateByPrimaryKey(ModelRelation record);
}