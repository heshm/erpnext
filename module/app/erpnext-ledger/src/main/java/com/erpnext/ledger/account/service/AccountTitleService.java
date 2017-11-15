package com.erpnext.ledger.account.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erpnext.ledger.account.domain.AccountTitle;

public interface AccountTitleService {
	
	AccountTitle readOneAccountTitle(String id);
	
	Page<AccountTitle> readPageAccountTitle(Pageable pageable,Map<String, Object> param);

}
