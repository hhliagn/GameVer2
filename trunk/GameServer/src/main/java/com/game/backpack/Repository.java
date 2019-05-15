package com.game.backpack;

import com.game.item.Item;
import com.game.utils.JsonUtil;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name = "repository")
@Table(appliesTo = "repository")
public class Repository {

    @Id
    private String accountId;
    private int capacity;
    private transient Map<Item, Integer> name2count;
    @Lob
    private transient byte[] itemsData;
    private static final int DEFAULT_SIZE = 150;

    public Repository() {
        this.capacity = DEFAULT_SIZE;
        this.name2count = new HashMap<>(this.capacity);
    }

    public Repository(int capacity){
        this.capacity = capacity;
        this.name2count = new HashMap<>(this.capacity);
    }

    public void doSerialize(){
        this.itemsData = JsonUtil.object2bytes(name2count);
    }

    public void doDeserialize(){
        this.name2count = JsonUtil.bytes2Object(itemsData, Map.class);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<Item, Integer> getName2count() {
        return name2count;
    }

    public void setName2count(Map<Item, Integer> name2count) {
        this.name2count = name2count;
    }

    public byte[] getItemsData() {
        return itemsData;
    }

    public void setItemsData(byte[] itemsData) {
        this.itemsData = itemsData;
    }
}
