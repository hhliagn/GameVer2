package com.game.backpack;

import com.game.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class BackpackService {

    @Autowired
    private BackpackDao backpackDao;

    @Autowired
    private RepositoryService repositoryService;

    private Map<String, Backpack> accountId2Backpack = new HashMap<>();

    public void init(){
        List<Backpack> backpacks = backpackDao.getList();
        for (Backpack backpack : backpacks) {
            accountId2Backpack.put(backpack.getAccountId(), backpack);
        }
    }

    public void printItems(Backpack backpack){
        Map<Item, Integer> name2count = backpack.getName2count();
        Set<Item> items = name2count.keySet();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private void userItem(String accountId, Item item){
        reduceItem(accountId, item);
    }

    private void removeItem(String accountId, Item item){
        Backpack backpack = getBackpack(accountId);
        if (backpack == null){
            return;
        }
        Map<Item, Integer> name2count = backpack.getName2count();
        name2count.remove(item);
    }

    public Backpack getBackpack(String accountId) {
        return accountId2Backpack.get(accountId);
    }

    public void addItem(String accountId, Item item){
        Backpack backpack = getBackpack(accountId);
        if (backpack == null){
            return;
        }
        Map<Item, Integer> name2countBpack = backpack.getName2count();
        Integer countBpack = name2countBpack.get(item);
        name2countBpack.put(item, countBpack + 1);
    }

    private void reduceItem(String accountId, Item item){
        Backpack backpack = getBackpack(accountId);
        if (backpack == null){
            return;
        }
        Map<Item, Integer> name2countBpack = backpack.getName2count();
        Integer countBpack = name2countBpack.get(item);
        if (countBpack != 0){
            name2countBpack.put(item, countBpack - 1);
        }
    }

    public void checkOutToRepo(String accountId, Item item){
        reduceItem(accountId, item);
        repositoryService.addItem(accountId, item);
    }
}
