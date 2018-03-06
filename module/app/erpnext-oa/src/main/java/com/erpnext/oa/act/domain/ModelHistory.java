package com.erpnext.oa.act.domain;

import java.util.Date;

public class ModelHistory extends AbstractModel{
    private String id;

    private String name;

    private String modelKey;

    private String description;

    private String modelComment;

    private Date created;

    private String createdBy;

    private Date lastUpdated;

    private String lastUpdatedBy;

    private Date removalDate;

    private Integer version;

    private String modelId;

    private Integer modelType;

    private String modelEditorJson;

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

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelComment() {
        return modelComment;
    }

    public void setModelComment(String modelComment) {
        this.modelComment = modelComment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(Date removalDate) {
        this.removalDate = removalDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public String getModelEditorJson() {
        return modelEditorJson;
    }

    public void setModelEditorJson(String modelEditorJson) {
        this.modelEditorJson = modelEditorJson;
    }
}