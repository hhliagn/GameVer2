package com.game.item;

import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "drug")
@Table(appliesTo = "drug")
public class Drug extends Item {

    @Id
    private long id;

    public Drug(String name, int consumable, int stackable) {
        this.name = name;
        this.consumable = consumable;
        this.stackable = stackable;
    }

    public Drug() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
