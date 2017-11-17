package com.erpnext.crm.customer.dto;

import java.util.List;

import com.erpnext.crm.customer.domain.CustomerGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class CustomerGroupDTO {
	
	private String id;

    private String parentId;

    private String name;

    private Boolean isGroup;

    private Byte status;
    
    private List<CustomerGroupDTO> children;
    
    public CustomerGroupDTO() {}
    
    public CustomerGroupDTO(CustomerGroup customerGroup) {
    	this.id = customerGroup.getId();
    	this.parentId = customerGroup.getParentId();
    	this.name = customerGroup.getName();
    	this.isGroup = customerGroup.getIsGroup();
    	this.status = customerGroup.getStatus();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public List<CustomerGroupDTO> getChildren() {
		return children;
	}

	public void setChildren(List<CustomerGroupDTO> children) {
		this.children = children;
	}

}
