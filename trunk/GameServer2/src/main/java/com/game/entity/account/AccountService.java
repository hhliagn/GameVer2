package com.game.entity.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public boolean createAccount(String accountId, String password){
        try {
            Account account = new Account(accountId, password, System.currentTimeMillis());
            accountDao.saveOrUpdate(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getLoginAccount(String accountId, String password){
        Account loginAccount = accountDao.getLoginAccount(accountId, password);
        if (loginAccount == null){
            return false;
        }
        return true;
    }
}
