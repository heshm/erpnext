package com.erpnext.oa.act.domain;

public abstract class AbstractModel {

	public static final int MODEL_TYPE_BPMN = 0;
	public static final int MODEL_TYPE_FORM = 2;
	public static final int MODEL_TYPE_APP = 3;
	public static final int MODEL_TYPE_DECISION_TABLE = 4;
	
	public abstract String getId();
	public abstract String getModelEditorJson();
}
