package com.game.account;

import com.game.SpringContext;
import com.game.player.Player;
import com.game.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    private Account loginAccount;

    public boolean createAccount(String accountId, String password){
        try {
            Account account = new Account(accountId, password, System.currentTimeMillis(), 1);
            accountDao.saveOrUpdate(account);
            setLoginAccount(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String accountId, String password){
        return getLoginAccount(accountId, password);
    }

    private boolean getLoginAccount(String accountId, String password){
        Account loginAccount = accountDao.getLoginAccount(accountId, password);
        if (loginAccount == null){
            return createAccount(accountId, password);
        }
        setLoginAccount(loginAccount);
        return true;
    }

    public List<Player> getPlayers(String accountId){
        return SpringContext.getBean("playerService", PlayerService.class).getPlayers(accountId);
    }

    public Account getAccount(String accountId){
        return accountDao.get(accountId);
    }

    public boolean saveAccount(Account account){
        try {
            accountDao.saveOrUpdate(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCurMap(String accountId){
        Account account = getAccount(accountId);
        return account.getCurMap();
    }

    public boolean createPlayer(String accountId, int job, int sex) {
        PlayerService playerService = SpringContext.getBean("playerService", PlayerService.class);
        return playerService.createPlayer(accountId, job, sex);
    }

    public void logout(String accountId) {
        //
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(Account loginAccount) {
        this.loginAccount = loginAccount;
    }

    public void changeNickName(String accountId, String nickName){
        if (accountId == null || nickName == null){
            return;
        }
        Account account = getAccount(accountId);
        if (account == null){
            return;
        }
        account.setNickName(nickName);
    }
}