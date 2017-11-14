package com.erpnext.stock.param.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.erpnext.stock.param.domain.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ItemDTO {
	
	private String itemId;
	@NotEmpty(message = "{item.itemGroupId.notEmpty}")
    private String itemGroupId;

	private String itemGroupName;

    private String name;

    private String unit;

    private String norm;

    @Size(max=15, message = "{item.rmrk.size}" )
    private String rmrk;

    private Short decNo;

    private String image;
    
    private Byte status;
    
    public ItemDTO(){
    	
    }
    
    public ItemDTO(Item item){
    	BeanUtils.copyProperties(item, this);
    }

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemGroupId() {
		return itemGroupId;
	}

	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

	public String getItemGroupName() {
		return itemGroupName;
	}

	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getRmrk() {
		return rmrk;
	}

	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}

	public Short getDecNo() {
		return decNo;
	}

	public void setDecNo(Short decNo) {
		this.decNo = decNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
