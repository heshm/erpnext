package com.erpnext.oa.act.domain;

import java.util.Date;

public abstract class AbstractModel {

	public static final int MODEL_TYPE_BPMN = 0;
	public static final int MODEL_TYPE_FORM = 2;
	public static final int MODEL_TYPE_APP = 3;
	public static final int MODEL_TYPE_DECISION_TABLE = 4;
	
	public abstract String getId();
	public abstract String getModelEditorJson();
	public abstract String getName();
	public abstract String getModelKey();
	public abstract String getDescription();
	public abstract Integer getVersion();
	public abstract Date getLastUpdated();
	public abstract String getLastUpdatedBy();
	public abstract String getCreatedBy();
	public abstract String getModelComment();
	public abstract Integer getModelType();
}
