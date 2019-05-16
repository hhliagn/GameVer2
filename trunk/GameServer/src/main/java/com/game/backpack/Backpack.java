package com.game.backpack;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.game.item.Drug;
import com.game.item.Equip;
import com.game.item.Item;
import com.game.utils.JsonUtil;
import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity(name = "backpack")
@Table(appliesTo = "backpack")
public class Backpack{

    @Id
    private String accountId;
    private int capacity;
    @JsonSerialize(keyUsing = JsonUtil.MyKeySerializer.class)
    private transient Map<Item, Integer> ItemIntegerMap;
    @Lob
    private String itemsData;
    private static final int DEFAULT_SIZE = 50;

    public Backpack() {
        this.capacity = DEFAULT_SIZE;
        this.ItemIntegerMap = new HashMap<>(this.capacity);
    }

    public Backpack(int capacity){
        this.capacity = capacity;
        this.ItemIntegerMap = new HashMap<>(this.capacity);
    }

    public void doSerialize(){
        this.itemsData = JsonUtil.object2json(ItemIntegerMap);
    }

    public void doDeserialize(){
        this.ItemIntegerMap = JsonUtil.json2Object(itemsData, Map.class);
        //新建一个map
        Map<Item, Integer> newItemIntegerMap= new HashMap<>();
        //获取所有Item
        Set<Map.Entry<Item, Integer>> entries = this.getItemIntegerMap().entrySet();
        //遍历所有的Item
        for (Map.Entry<Item, Integer> entry : entries) {
            //获取到Item
            String item = String.valueOf(entry.getKey());
            //获取Item对应的数量
            Integer value = entry.getValue();
            //先转成map获取类型
            Map map = JsonUtil.json2Object(item, Map.class);
            Integer type = (Integer) map.get("type");
            //判断类型, 根据类型再将Item 转为对应类型, 并加入新的map中
            if (type == 2){
                Equip equipStored = JsonUtil.json2Object(item, Equip.class);
                newItemIntegerMap.put(equipStored, value);
            }
            if (type == 1){
                Drug drugStored = JsonUtil.json2Object(item, Drug.class);
                newItemIntegerMap.put(drugStored, value);
            }
        }
        //设置为新的Map
        this.setItemIntegerMap(newItemIntegerMap);
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

   /* public byte[] getItemsData() {
        return itemsData;
    }

    public void setItemsData(byte[] itemsData) {
        this.itemsData = itemsData;
    }*/

    public String getItemsData() {
        return itemsData;
    }

    public void setItemsData(String itemsData) {
        this.itemsData = itemsData;
    }
}
