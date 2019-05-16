package com.game.item;

import com.game.player.Player;

public class Drug extends Item {

//    private String extra;
////
////    public String getExtra() {
////        return extra;
////    }
////
////    public void setExtra(String extra) {
////        this.extra = extra;
////    }


    @Override
    public void useItem(Player player) {
        System.out.println("使用药品");
    }
}
