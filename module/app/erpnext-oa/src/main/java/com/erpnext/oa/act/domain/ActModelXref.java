package com.erpnext.oa.act.domain;

import com.erpnext.framework.util.IDUtils;

public class ActModelXref {
    private String id;

    private String appId;

    private String modelId;
    
    public ActModelXref() {};
    
    public ActModelXref(String appId,String modelId) {
    	this.appId = appId;
    	this.modelId = modelId;
    	this.id = IDUtils.uuid();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}