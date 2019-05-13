package com.game.player;

import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "player")
@Table(appliesTo = "Player")
public class Player {

    @Id
    private long id;
    private String accountId;
    private int status;
    private String name;
    private int sex;
    private int job;
    private long createTime;

    public Player() {
    }

    public Player(long id, String accountId, int status, String name, int sex, int job, long createTime) {
        this.id = id;
        this.accountId = accountId;
        this.status = status;
        this.name = name;
        this.sex = sex;
        this.job = job;
        this.createTime = createTime;
    }

    public Player(long id, String accountId) {
        this.id = id;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}