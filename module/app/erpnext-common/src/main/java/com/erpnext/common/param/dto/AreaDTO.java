package com.erpnext.common.param.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.erpnext.common.param.domain.Area;

public class AreaDTO extends Area{
	
	private String typeName;
	
	private List<AreaDTO> children;
	
	public AreaDTO(){
	}
	
	public AreaDTO(Area area){
		BeanUtils.copyProperties(area, this);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<AreaDTO> getChildren() {
		return children;
	}

	public void setChildren(List<AreaDTO> children) {
		this.children = children;
	}
	
}
