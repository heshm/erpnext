package com.erpnext.common.setup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Department {
    private String id;

    private String parentId;

    private String name;

    private String areaId;

    private String type;

    private String code;

    private String grade;

    private String primaryPerson;

    private String addr;

    private String telNo;

    private String faxNo;

    private String email;

    private String remark;

    @JsonIgnore
    private Boolean delFlg;

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPrimaryPerson() {
        return primaryPerson;
    }

    public void setPrimaryPerson(String primaryPerson) {
        this.primaryPerson = primaryPerson;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

	@Override
	public String toString() {
		return "Department [id=" + id + ", parentId=" + parentId + ", name=" + name + ", areaId=" + areaId + ", type="
				+ type + ", code=" + code + ", grade=" + grade + ", primaryPerson=" + primaryPerson + ", addr=" + addr
				+ ", telNo=" + telNo + ", faxNo=" + faxNo + ", email=" + email + ", remark=" + remark + ", delFlg="
				+ delFlg + "]";
	}
}