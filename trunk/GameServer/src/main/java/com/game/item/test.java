package com.game.item;

import com.game.SpringContext;
import com.game.backpack.Backpack;
import com.game.backpack.BackpackDao;
import com.game.utils.JsonUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.java2d.pipe.SpanClipRenderer;

import java.util.*;

public class test {

    public static void main(String[] args) {

//        ArrayList<Object> objects = new ArrayList<>();
//        Item item1 = new Item();
//        item1.setName("aa");
//        Item item2 = new Item();
//        item2.setName("b");
//        Item item3 = new Item();
//        item3.setName("c");
//        objects.add(item1);
//        objects.add(item2);
//        objects.add(item3);
//        for (Object object : objects) {
//            if (object instanceof Item){
//                System.out.println(item3);
//            }
//        }

//        Drug drug = new Drug();
//        drug.setExtra("ccc");
//        Item item = drug;
//        Drug drug1 = (Drug) item;
//        System.out.println(drug1.getExtra());

//        Equip equip = new Equip();
//        equip.setPosition(1);
//        //Item item = equip;
//        Backpack backpack = new Backpack();
//        backpack.setAccountId("lhh");
//        Map<Item, Integer> itemIntegerMap = backpack.getItemIntegerMap();
//        itemIntegerMap.put(equip, 10);
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        BackpackDao backpackDao = SpringContext.getBean("backpackDao");
//        backpackDao.saveOrUpdate(backpack);
//        Backpack backpack1 = backpackDao.get("lhh");
//        System.out.println();
//        Map<Item, Integer> newItemIntegerMap= new HashMap<>();
//        Set<Map.Entry<Item, Integer>> entries = backpack1.getItemIntegerMap().entrySet();
//        for (Map.Entry<Item, Integer> entry : entries) {
//            Map map = (Map)entry.getKey();
////            String item = String.valueOf(entry.getKey());
////            Integer value = entry.getValue();
////            Map map = JsonUtil.json2Object(item, Map.class);
//            Integer type = (Integer) map.get("type");
////            if (type == 2){
////                Equip equipStored = JsonUtil.json2Object(item, Equip.class);
////                newItemIntegerMap.put(equipStored, value);
////            }
////            if (type == 1){
////                Drug drugStored = JsonUtil.json2Object(item, Drug.class);
////                newItemIntegerMap.put(drugStored, value);
////            }
//        }
//        backpack1.setItemIntegerMap(newItemIntegerMap);

        Item item = new Item();
        Map<String,Integer> map = new HashMap<>();
        map.put("ccc",11);
        map.put("cac",12);
        item.setConditionMap(map);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ItemDao itemDao = SpringContext.getBean("itemDao");
        itemDao.saveOrUpdate(item);
        Item item1 = itemDao.get(1);
        System.out.println();
    }
}
