package com.erpnext.framework.manager;

public interface SequenceManager {
	/** 
	 * @param 序号类型
	 * @return 当前序号
	 */
	int nextIntegerSequence(String type);
	/** 
	 * @param 序号类型
	 * @return 当前序号
	 */
	String nextStringSequence(String type);
	
	
	

}
