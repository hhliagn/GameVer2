package com.game.item;

public abstract class Item {

    protected String name;
    protected int consumable;
    protected int stackable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
