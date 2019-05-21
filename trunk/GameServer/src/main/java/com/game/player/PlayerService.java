package com.game.player;

import com.game.account.Account;
import com.game.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private LevelExpDao levelExpDao;

    @Autowired
    private AccountService accountService;

    private Map<String, List<Player>> accountId2Players = new HashMap<String, List<Player>>();

    public List<Player> getPlayers(String accountId){
        List<Player> players = accountId2Players.get(accountId);
        if (players == null){
            players = playerDao.getPlayers(accountId);
            if (players == null){
                players = new ArrayList<Player>();
            }
            accountId2Players.put(accountId, players);
        }
        return players;
    }

    public boolean createPlayer(String accountId, int sex, int job){
        try {
            Long id = getId();
            String name = getName(job, sex);
            createPlayer(id, accountId, 1, name, sex, job, System.currentTimeMillis());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Long getId() {
        return playerDao.getLast() + 1;
    }

    private String getName(int job, int sex) {
        String str1 = "";
        String str2 = "";
        switch (sex) {
            case 1:
                str1 = "男";
                break;
            case 2:
                str1 = "女";
                break;
        }
        switch (job){
            case 1:
                str2 = "战士";
                break;
            case 2:
                str2 = "法师";
                break;
            case 3:
                str2 = "刺客";
                break;
        }
        return str1 + str2;
    }

    private void createPlayer(Long id, String accountId, int status, String name, int sex, int job, long createTime){
        Player player = new Player(id, accountId, status, name, sex, job, createTime);
        player.setLevel(1);
        playerDao.saveOrUpdate(player);
        addPlayer(player);
        Account account = accountService.getAccount(accountId);
        account.setRecentPlayerId(player.getId());
    }

    private void addPlayer(Player player) {
        String accountId = player.getAccountId();
        List<Player> players = accountId2Players.get(accountId);
        if (players == null){
            players = new ArrayList<>();
        }
        players.add(player);
        accountId2Players.put(accountId, players);
    }

    public Player getPlayer(long recentPlayerId) {
        return playerDao.get(recentPlayerId);
    }

    public void levelUp(Player player, long exp){
        long playerExp = player.getExp();
        long totalExp = playerExp + exp;
        LevelExp levelExp = levelExpDao.getMaxLevel(totalExp);
        int level = levelExp.getLevel();
        int playerLevel = player.getLevel();
        if (playerLevel > level){
            return;
        }
        player.setLevel(level);
    }
}
