package com.game.backpack;

import com.game.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RepositoryService {

    @Autowired
    private RepositoryDao repositoryDao;

    @Autowired
    private BackpackService backpackService;

    private Map<String, Repository> accountId2Repository = new HashMap<>();

    public void init(){
        List<Repository> repositories = repositoryDao.getList();
        for (Repository repository : repositories) {
            accountId2Repository.put(repository.getAccountId(), repository);
        }
    }

    public void printItems(Repository repository){
        Map<Item, Integer> name2count = repository.getItemIntegerMap();
        Set<Item> items = name2count.keySet();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void addItem(String accountId, Item item){
        Repository repository = getRepository(accountId);
        if (repository == null){
            return;
        }
        Map<Item, Integer> name2countRepo = repository.getItemIntegerMap();
        Integer countRepo = name2countRepo.get(item);
        name2countRepo.put(item, countRepo + 1);
    }

    private void reduceItem(String accountId, Item item){
        Repository repository = getRepository(accountId);
        if (repository == null){
            return;
        }
        Map<Item, Integer> name2countRepo = repository.getItemIntegerMap();
        Integer countRepo = name2countRepo.get(item);
        if (countRepo != 0){
            name2countRepo.put(item, countRepo - 1);
        }
    }

    public void checkOutToBpack(String accountId, Item item){
        reduceItem(accountId, item);
        backpackService.addItem(accountId, item);
    }

    private void removeItem(String accountId, Item item){
        Repository repository = getRepository(accountId);
        if (repository == null){
            return;
        }
        Map<Item, Integer> name2count = repository.getItemIntegerMap();
        name2count.remove(item);
    }

    public Repository getRepository(String accountId) {
        return accountId2Repository.get(accountId);
    }
}
