package com.game.item;


import com.game.player.Player;
import com.game.utils.JsonUtil;
import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Map;

@Entity(name = "item")
@Table(appliesTo = "item")
public class Item {

    @Id
    private long id;
    private String name;
    private int type;
    private int consumable;
    private int stackable;

    private transient Map<String, Integer> conditionMap;
    @Lob
    private String conditionMapData;

    public void doSerialize(){
        this.conditionMapData = JsonUtil.object2json(conditionMap);
    }

    public void doDeserialize(){
        this.conditionMap = JsonUtil.json2Object(conditionMapData, Map.class);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getConsumable() {
        return consumable;
    }

    public void setConsumable(int consumable) {
        this.consumable = consumable;
    }

    public int getStackable() {
        return stackable;
    }

    public void setStackable(int stackable) {
        this.stackable = stackable;
    }

    public void useItem(Player player){
        if (canUseItem(player) == false){
            return;
        }
    }

    public boolean canUseItem(Player player){
        return true;
    }

    public Map<String, Integer> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, Integer> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public String getConditionMapData() {
        return conditionMapData;
    }

    public void setConditionMapData(String conditionMapData) {
        this.conditionMapData = conditionMapData;
    }
}
