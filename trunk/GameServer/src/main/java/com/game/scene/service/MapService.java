package com.game.scene.service;

import com.game.SpringContext;
import com.game.account.Account;
import com.game.account.AccountService;
import com.game.scene.entity.MapEnt;
import com.game.scene.entity.MapEntDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class MapService{

    private static final Logger logger = LoggerFactory.getLogger("map");

    @Autowired
    private MapEntDao mapEntDao;

    private HashMap<Integer, MapEnt> id2Map = new HashMap<>();

    private HashMap<String, MapEnt> name2Map = new HashMap<>();

    public void initMapData() {
        List<MapEnt> mapEntList = mapEntDao.getList();
        for (MapEnt mapEnt : mapEntList) {
            id2Map.put(mapEnt.getMapId(), mapEnt);
            name2Map.put(mapEnt.getMapName(), mapEnt);
        }
    }

    public void saveMapEnt(MapEnt mapEnt) {
        mapEntDao.saveOrUpdate(mapEnt);
    }

    public MapEnt getMapEntById(int id){
        return id2Map.get(id);
    }

    public MapEnt getMapEntByName(String mapName){
        return name2Map.get(mapName);
    }

    public boolean isAccountCurMap(String accountId, int mapId) {
        AccountService accountService = SpringContext.getBean("accountService");
        int curMap = accountService.getCurMap(accountId);
        if (curMap != mapId){
            return false;
        }
        return true;
    }

    public void changeMap(String accountId, String mapName) {
        MapEnt mapEnt = getMapEntByName(mapName);
        if (mapEnt == null){
            throw new RuntimeException("地图不存在");
        }
        int mapId = mapEnt.getMapId();
        boolean b = canEnter(accountId, mapId);
        if (b){
            AccountService accountService = SpringContext.getBean("accountService");
            Account account = accountService.getAccount(accountId);
            account.setCurMap(mapId);
            accountService.saveAccount(account);
        }else {
            throw new RuntimeException("地图不在附近");
        }
    }

    public int name2Id(String mapName) {
        MapEnt mapEnt = getMapEntByName(mapName);
        int mapId = mapEnt.getMapId();
        return mapId;
    }

    private boolean canEnter(String accountId, int mapId) {
        AccountService accountService = SpringContext.getBean("accountService");
        int curMapId = accountService.getCurMap(accountId);
        MapEnt curMap = getMapEntById(curMapId);
        List<Integer> mapNearByIds = curMap.getMapNearByIds();
        for (Integer mapNearById : mapNearByIds) {
            if (mapNearById == mapId){
                return true;
            }
        }
        return false;
    }
}
