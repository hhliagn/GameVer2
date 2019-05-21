package com.game.player;

import com.game.item.Equip;
import com.game.utils.JsonUtil;
import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;
import java.util.Map;

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
    private int level;
    private long exp;

    private transient Map<Integer, Equip> equipMap;
    @Lob
    private byte[] equipData;

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

    public void doSerialize(){
        this.equipData = JsonUtil.object2bytes(equipMap);
    }

    public void doDeserialize(){
        this.equipMap = JsonUtil.bytes2Object(equipData, Map.class);
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

    public Map<Integer, Equip> getEquipMap() {
        return equipMap;
    }

    public void setEquipMap(Map<Integer, Equip> equipMap) {
        this.equipMap = equipMap;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public byte[] getEquipData() {
        return equipData;
    }

    public void setEquipData(byte[] equipData) {
        this.equipData = equipData;
    }
}
