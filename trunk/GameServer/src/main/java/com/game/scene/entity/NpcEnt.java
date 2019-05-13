package com.game.scene.entity;

import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "npc")
@Table(appliesTo = "npc")
public class NpcEnt {

    @Id
    private long id;
    private String name;
    private int mapId;
    private int x;
    private int y;
    private int status;

    public NpcEnt() {
    }

    public NpcEnt(long id, String name, int mapId, int x, int y, int status) {
        this.id = id;
        this.name = name;
        this.mapId = mapId;
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public boolean isAlive(){
        if (status == 0) return false;
        if (status == 1) return true;
        return true;
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

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
