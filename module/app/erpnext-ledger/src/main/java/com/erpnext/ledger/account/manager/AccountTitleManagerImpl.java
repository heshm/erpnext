package com.erpnext.ledger.account.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.ledger.account.domain.AccountTitle;
import com.erpnext.ledger.account.mapper.AccountTitleMapper;

@Component
@Transactional(readOnly=true) 
public class AccountTitleManagerImpl implements AccountTitleManager {

	@Autowired
	private AccountTitleMapper accountTitleMapper;
	
	@Override
	public AccountTitle readOneAccountTitle(String id) {
		return accountTitleMapper.selectByPrimaryKey(id);
	}

}
