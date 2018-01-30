
package com.erpnext.oa.act.dto;

import java.util.Date;

/**
 * Representation of process-models, both current and historic models.
 * 
 * @author Tijs Rademakers
 */
public class ModelRepresentation {

  protected String id;
  protected String name;
  protected String key;
  protected String description;
  protected String createdBy;
  protected String lastUpdatedBy;
  protected Date lastUpdated;
  protected boolean latestVersion;
  protected int version;
  protected String comment;
  protected Integer modelType;

 

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

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public void setLatestVersion(boolean latestVersion) {
    this.latestVersion = latestVersion;
  }

  public boolean isLatestVersion() {
    return latestVersion;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getComment() {
    return comment;
  }

  public Integer getModelType() {
    return modelType;
  }

  public void setModelType(Integer modelType) {
    this.modelType = modelType;
  }

 
}
