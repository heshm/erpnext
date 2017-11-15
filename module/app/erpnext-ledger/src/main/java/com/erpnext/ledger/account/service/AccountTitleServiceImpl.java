package com.erpnext.ledger.account.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.ledger.account.domain.AccountTitle;
import com.erpnext.ledger.account.manager.AccountTitleManager;
import com.erpnext.ledger.account.mapper.AccountTitleMapper;

@Service
@Transactional(readOnly = true)
public class AccountTitleServiceImpl implements AccountTitleService {

	@Autowired
	private AccountTitleManager accountTitleManager;
	@Autowired
	private AccountTitleMapper accountTitleMapper;
	
	@Override
	public AccountTitle readOneAccountTitle(String id) {
		return accountTitleManager.readOneAccountTitle(id);
	}

	@Override
	public Page<AccountTitle> readPageAccountTitle(Pageable pageable,Map<String, Object> param) {
		param.put("pageable", pageable);
		List<AccountTitle> list = accountTitleMapper.selectList(param);
		int total = accountTitleMapper.countByExample(param);
		return new PageImpl<AccountTitle>(list,pageable,total);
	}

}
