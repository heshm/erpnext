package com.erpnext.common.param.dto;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.erpnext.common.param.domain.Area;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AreaDTO extends Area{
	
	private String typeName;
	//@JsonInclude(Include.NON_EMPTY)
	private List<AreaDTO> children;
	
	public AreaDTO(){
	}
	
	public AreaDTO(Area area){
		BeanUtils.copyProperties(area, this);
		children = new LinkedList<>(); 
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
