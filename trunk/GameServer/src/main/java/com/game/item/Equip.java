package com.game.item;

import com.game.SpringContext;
import com.game.backpack.BackpackService;
import com.game.player.Player;
import java.util.Map;

public class Equip extends Item {

    private long playerId;
    private int position;

    public Equip() {
        this.setType(2);
    }

    public void takeOn(Player player){
        Map<Integer, Equip> equipMap = player.getEquipMap();
        Equip previousEquip = equipMap.put(this.getPosition(), this);
        //如果先前玩家这个位置有装备, 就把旧的装备加入背包
        if (previousEquip != null){
            String accountId = player.getAccountId();
            BackpackService backpackService = SpringContext.getBean("backpackService");
            backpackService.addItem(accountId, this);
        }
        System.out.println("穿上装备");
    }

    public void takeOff(Player player){
        Map<Integer, Equip> equipMap = player.getEquipMap();
        equipMap.remove(this.getPosition());
        String accountId = player.getAccountId();
        BackpackService backpackService = SpringContext.getBean("backpackService");
        backpackService.addItem(accountId, this);
        System.out.println("脱下装备");
    }

    @Override
    public void useItem(Player player) {
        takeOn(player);
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
