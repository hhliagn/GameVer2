package com.game.entity.account;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account")
@Table(appliesTo = "account")
public class Account {

    @Id
    private String accountId;
    private String password;
    private long creatime;

    public Account() {
    }

    public Account(String accountId, String password, long creatime) {
        this.accountId = accountId;
        this.password = password;
        this.creatime = creatime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatime() {
        return creatime;
    }

    public void setCreatime(long creatime) {
        this.creatime = creatime;
    }
}
