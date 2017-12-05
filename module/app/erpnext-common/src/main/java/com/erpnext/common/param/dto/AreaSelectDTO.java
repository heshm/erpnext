package com.erpnext.common.param.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AreaSelectDTO {
	
	private String value;
	
	private String label;
	@JsonInclude(Include.NON_EMPTY)
	private List<AreaSelectDTO> children;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<AreaSelectDTO> getChildren() {
		return children;
	}

	public void setChildren(List<AreaSelectDTO> children) {
		this.children = children;
	}
	
	

}
