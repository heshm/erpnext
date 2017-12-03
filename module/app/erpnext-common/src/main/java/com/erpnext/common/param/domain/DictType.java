package com.erpnext.common.param.domain;

import java.io.Serializable;

public class DictType implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2995042440379880168L;

	private String id;

    private String name;
    
    private Integer valueLength;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getValueLength() {
		return valueLength;
	}

	public void setValueLength(Integer valueLength) {
		this.valueLength = valueLength;
	}
    
    
}