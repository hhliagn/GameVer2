package com.game.item;

import com.game.player.Player;

import java.util.Map;
import java.util.Set;

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

    public Drug() {
        this.setType(1);
    }

    @Override
    public void useItem(Player player) {
        if (canUseItem(player) == false){
            return;
        }
        System.out.println("使用药品");
    }

    @Override
    public boolean canUseItem(Player player) {
        Map<String, Integer> conditionMap = this.getConditionMap();
        Set<Map.Entry<String, Integer>> conditions = conditionMap.entrySet();
        for (Map.Entry<String, Integer> condition : conditions) {
            String conditionType = condition.getKey();
            Integer value = condition.getValue();
            if (conditionType.equals("性别")){
                int sex = player.getSex();
                if (sex != value){
                    return false;
                }
            }
            if (conditionType.equals("职业")){
                int job = player.getJob();
                if (job != value){
                    return false;
                }
            }
            if (conditionType.equals("等级")){
                int level = player.getLevel();
                if (level < value){
                    return false;
                }
            }
        }
        return true;
    }
}
