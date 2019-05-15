package com.game;

import com.game.scene.entity.*;
import com.game.scene.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GlobalService {

    @Autowired
    private MonsterDao monsterDao;

    @Autowired
    private NpcDao npcDao;

    public void writeData(){

        //地图数据
        MapEnt mapEnt0 = new MapEnt(1, "启始之地", Arrays.<Long>asList(), Arrays.asList(11L), Arrays.asList(2));
        MapEnt mapEnt1 = new MapEnt(2, "村子", Arrays.<Long>asList(), Arrays.asList(21L, 22L), Arrays.asList(3, 4));
        MapEnt mapEnt2 = new MapEnt(3, "森林", Arrays.asList(31L, 32L, 33L, 34L, 35L), Arrays.asList(31L, 32L), Arrays.asList(2));
        MapEnt mapEnt3 = new MapEnt(4, "传说秘境", Arrays.asList(41L, 42L, 43L, 44L, 45L), Arrays.asList(41L), Arrays.asList(2));
        MapService mapService = SpringContext.getBean("mapService", MapService.class);
        mapService.saveMapEnt(mapEnt0);
        mapService.saveMapEnt(mapEnt1);
        mapService.saveMapEnt(mapEnt2);
        mapService.saveMapEnt(mapEnt3);

        //怪物数据
        MonsterEnt monsterEnt0 = new MonsterEnt(31L, "头狼", 3, 5, 5 ,1);
        MonsterEnt monsterEnt1 = new MonsterEnt(32L, "狼", 3, 5, 4, 1);
        MonsterEnt monsterEnt2 = new MonsterEnt(33L, "狼", 3, 5, 6, 1);
        MonsterEnt monsterEnt3 = new MonsterEnt(34L, "狼", 3, 4, 5, 1);
        MonsterEnt monsterEnt4 = new MonsterEnt(35L, "狼", 3, 6, 5, 1);

        MonsterEnt monsterEnt5 = new MonsterEnt(41L, "强盗", 4, 5, 5, 1);
        MonsterEnt monsterEnt6 = new MonsterEnt(42L, "守卫", 4, 5, 4, 1);
        MonsterEnt monsterEnt7 = new MonsterEnt(43L, "守卫", 4, 5, 6, 1);
        MonsterEnt monsterEnt8 = new MonsterEnt(44L, "守卫", 4, 4, 5, 1);
        MonsterEnt monsterEnt9 = new MonsterEnt(45L, "守卫", 4, 6, 5, 1);
        monsterDao.saveOrUpdate(monsterEnt0);
        monsterDao.saveOrUpdate(monsterEnt1);
        monsterDao.saveOrUpdate(monsterEnt2);
        monsterDao.saveOrUpdate(monsterEnt3);
        monsterDao.saveOrUpdate(monsterEnt4);
        monsterDao.saveOrUpdate(monsterEnt5);
        monsterDao.saveOrUpdate(monsterEnt6);
        monsterDao.saveOrUpdate(monsterEnt7);
        monsterDao.saveOrUpdate(monsterEnt8);
        monsterDao.saveOrUpdate(monsterEnt9);

        //npc数据
        NpcEnt npcEnt0 = new NpcEnt(11L, "新手指导员", 1, 10, 10, 1);
        NpcEnt npcEnt1 = new NpcEnt(21L, "村长", 2, 10, 10, 1);
        NpcEnt npcEnt2 = new NpcEnt(22L, "铁匠", 2, 20, 20, 1);
        NpcEnt npcEnt3 = new NpcEnt(31L, "守林人", 3, 10, 10, 1);
        NpcEnt npcEnt4 = new NpcEnt(32L, "农夫", 3, 20, 20, 1);
        NpcEnt npcEnt5 = new NpcEnt(41L, "神秘人", 4, 10, 10, 1);
        npcDao.saveOrUpdate(npcEnt0);
        npcDao.saveOrUpdate(npcEnt1);
        npcDao.saveOrUpdate(npcEnt2);
        npcDao.saveOrUpdate(npcEnt3);
        npcDao.saveOrUpdate(npcEnt4);
        npcDao.saveOrUpdate(npcEnt5);
    }
}
