package com.erpnext.common.setup.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.erpnext.common.param.domain.Area;
import com.erpnext.common.setup.domain.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class DepartmentDTO extends Department{
	
	private String typeName;
	
	private String primaryPersonName;
	
	private Area area;
	
	@JsonProperty("children")
	private List<Department> childDepartment;
	
	public DepartmentDTO(){
		this.childDepartment = new ArrayList<>();
	}
	
	public DepartmentDTO(Department depart){
		BeanUtils.copyProperties(depart, this);
		this.childDepartment = new ArrayList<>();
	}
	
	public DepartmentDTO(Department depart,Area area){
		this(depart);
		this.area = area;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getPrimaryPersonName() {
		return primaryPersonName;
	}

	public Area getArea() {
		return area;
	}

	public List<Department> getChildDepartment() {
		return childDepartment;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setPrimaryPersonName(String primaryPersonName) {
		this.primaryPersonName = primaryPersonName;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void setChildDepartment(List<Department> childDepartment) {
		this.childDepartment = childDepartment;
	}

}
