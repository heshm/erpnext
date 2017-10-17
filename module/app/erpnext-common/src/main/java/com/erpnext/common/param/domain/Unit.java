package com.erpnext.common.param.domain;

import javax.validation.constraints.NotBlank;

public class Unit {
	@NotBlank
    private String id;
	@NotBlank
    private String name;

    private String sign;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

	@Override
	public String toString() {
		return "Unit [id=" + id + ", name=" + name + ", sign=" + sign + "]";
	}
    
    
}