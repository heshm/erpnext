package com.erpnext.common.param.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Unit {
	@NotBlank(message = "{unit.id.notBlank}")
    private String id;
	@NotBlank(message = "{unit.name.notBlank}")
    private String name;
	@Size(min=0,max=4,message="{unit.sign.size}")
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