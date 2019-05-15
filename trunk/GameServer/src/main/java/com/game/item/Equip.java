package com.game.item;

import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "equip")
@Table(appliesTo = "equip")
public class Equip extends Item {

    @Id
    private int id;

    public Equip(String name, int consumable, int stackable) {
        this.name = name;
        this.consumable = consumable;
        this.stackable = stackable;
    }

    public Equip() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
