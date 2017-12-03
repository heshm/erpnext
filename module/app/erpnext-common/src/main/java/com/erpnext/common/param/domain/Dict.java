package com.erpnext.common.param.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class Dict implements Serializable{
	
	private static final long serialVersionUID = 4427621780613480355L;

	private String dictType;

    private String dictValue;

    private String dictLabel;

    private String remark;
    
    private DictType type;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public DictType getType() {
		return type;
	}

	public void setType(DictType type) {
		this.type = type;
	}
}