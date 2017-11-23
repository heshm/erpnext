package com.erpnext.common.param.manager;

import com.erpnext.common.param.domain.Dict;

public interface DictManager {
	
	Dict readOneDict(String dictType,String dictValue);

}
