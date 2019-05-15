package com.game.scene.service;

import com.game.SpringContext;
import com.game.account.Account;
import com.game.account.AccountService;
import com.game.scene.Scene;
import com.game.scene.entity.MonsterDao;
import com.game.scene.entity.MonsterEnt;
import com.game.scene.entity.NpcDao;
import com.game.scene.entity.NpcEnt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SceneService{

    private static Logger logger = LoggerFactory.getLogger("scene");

    @Autowired
    private MonsterDao monsterDao;
    @Autowired
    private NpcDao npcDao;

    private Map<String, Scene> accountId2Scene = new HashMap<>();

    public void enter(String accountId, int mapId) {
        List<MonsterEnt> monsterEnts = monsterDao.findByMapId(mapId);
        List<NpcEnt> npcEnts = npcDao.findByMapId(mapId);
        AccountService accountService = SpringContext.getBean("accountService");
        Account account = accountService.getAccount(accountId);
        Scene scene = createScene(mapId, accountId, monsterEnts, npcEnts, account);
        addScene(scene);
        logger.info("进入场景");
    }

    private Scene createScene(int mapId, String accountId, List<MonsterEnt> monsterEnts, List<NpcEnt> npcEnts, Account account) {
        return new Scene(mapId, accountId, monsterEnts, npcEnts, account);
    }

    public Scene getScene(String accountId){
        return accountId2Scene.get(accountId);
    }

    private void addScene(Scene scene) {
        accountId2Scene.put(scene.getAccountId(), scene);
    }
}
