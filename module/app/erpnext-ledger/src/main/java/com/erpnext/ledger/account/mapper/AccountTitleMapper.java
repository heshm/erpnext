package com.erpnext.ledger.account.mapper;

import com.erpnext.ledger.account.domain.AccountTitle;
import java.util.List;
import java.util.Map;

public interface AccountTitleMapper {
    int deleteByPrimaryKey(String accountNo);

    int insert(AccountTitle record);

    AccountTitle selectByPrimaryKey(String accountNo);

    List<AccountTitle> selectAll();
    
    List<AccountTitle> selectList(Map<String,Object> map);

    int updateByPrimaryKey(AccountTitle record);
    
    int countByExample(Map<String,Object> map);
    
}