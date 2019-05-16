package com.game.account;

import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account")
@Table(appliesTo = "account")
public class Account {

    @Id
    private String accountId;
    private String password;
    private long createTime;
    private int curMap;
    private String nickName;

    private transient int x;
    private transient int y;

    private long recentPlayerId;

    public Account() {
    }

    public Account(String accountId, String password, long createTime, int curMap) {
        this.accountId = accountId;
        this.password = password;
        this.createTime = createTime;
        this.curMap = curMap;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCurMap() {
        return curMap;
    }

    public void setCurMap(int curMap) {
        this.curMap = curMap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getRecentPlayerId() {
        return recentPlayerId;
    }

    public void setRecentPlayerId(long recentPlayerId) {
        this.recentPlayerId = recentPlayerId;
    }
}
