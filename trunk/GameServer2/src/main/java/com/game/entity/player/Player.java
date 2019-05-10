package com.game.entity.player;

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

    public Player() {
    }

    public Player(long id, String accountId, int status) {
        this.id = id;
        this.accountId = accountId;
        this.status = status;
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
}
