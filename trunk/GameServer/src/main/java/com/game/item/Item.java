package com.game.item;


import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "item")
@Table(appliesTo = "item")
public class Item {

    @Id
    private long id;
    private String name;
    private int type;
    private int consumable;
    private int stackable;

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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", consumable=" + consumable +
                ", stackable=" + stackable +
                '}';
    }
}
