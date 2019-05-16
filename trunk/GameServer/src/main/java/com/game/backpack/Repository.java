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
    private transient Map<Item, Integer> ItemIntegerMap;
    @Lob
    private transient byte[] itemsData;
    private static final int DEFAULT_SIZE = 50;

    public Repository() {
        this.capacity = DEFAULT_SIZE;
        this.ItemIntegerMap = new HashMap<>(this.capacity);
    }

    public Repository(int capacity){
        this.capacity = capacity;
        this.ItemIntegerMap = new HashMap<>(this.capacity);
    }

    public void doSerialize(){
        this.itemsData = JsonUtil.object2bytes(ItemIntegerMap);
    }

    public void doDeserialize(){
        this.ItemIntegerMap = JsonUtil.bytes2Object(itemsData, Map.class);
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

    public Map<Item, Integer> getItemIntegerMap() {
        return ItemIntegerMap;
    }

    public void setItemIntegerMap(Map<Item, Integer> ItemIntegerMap) {
        this.ItemIntegerMap = ItemIntegerMap;
    }

    public byte[] getItemsData() {
        return itemsData;
    }

    public void setItemsData(byte[] itemsData) {
        this.itemsData = itemsData;
    }
}
