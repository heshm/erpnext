
package com.erpnext.oa.act.dto;

import java.util.Date;

import com.erpnext.oa.act.domain.AbstractModel;
import com.erpnext.oa.act.domain.Model;
import com.erpnext.oa.act.domain.ModelHistory;

/**
 * Representation of process-models, both current and historic models.
 * 
 * @author Tijs Rademakers
 */
public class ModelRepresentation {

	private String id;
	private String name;
	private String key;
	private String description;
	private String createdBy;
	private String lastUpdatedBy;
	private Date lastUpdated;
	private boolean latestVersion;
	private int version;
	private String comment;
	private Integer modelType;

	public ModelRepresentation() {

	}

	public ModelRepresentation(AbstractModel model) {
		initialize(model);
	}

	public void initialize(AbstractModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.key = model.getModelKey();
		this.description = model.getDescription();
		this.createdBy = model.getCreatedBy();
		this.lastUpdated = model.getLastUpdated();
		this.version = model.getVersion();
		this.lastUpdatedBy = model.getLastUpdatedBy();
		this.comment = model.getModelComment();
		this.modelType = model.getModelType();

		if (model instanceof Model) {
			this.setLatestVersion(true);
		} else if (model instanceof ModelHistory) {
			this.setLatestVersion(false);
		}
	}

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
