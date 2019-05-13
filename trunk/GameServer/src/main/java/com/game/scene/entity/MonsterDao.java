package com.game.scene.entity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class MonsterDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public MonsterEnt get(long id) {
        String hql = "select p from MonsterEnt p where id = ?";
        Query query = getSession().createQuery(hql).setLong(0, id);
        MonsterEnt monsterEnt = (MonsterEnt) query.uniqueResult();
        return monsterEnt;
    }

    //查询列表
    public List<MonsterEnt> getList() {
        String hql = "FROM MonsterEnt";
        Query query = getSession().createQuery(hql);
        List<MonsterEnt> monsterEntList = query.list();
        return monsterEntList;
    }

    //新增/更新
    public void saveOrUpdate(MonsterEnt monsterEnt) {
        getSession().saveOrUpdate(monsterEnt);
    }

    //删除
    public void delete(MonsterEnt monsterEnt){
        try {
            getSession().delete(monsterEnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据ID查找
    public List<MonsterEnt> findByMapId(int mapId) {
        String hql = "select p from MonsterEnt p where mapId = ?";
        Query query = getSession().createQuery(hql).setInteger(0, mapId);
        List<MonsterEnt> monsterEntList = query.list();
        return monsterEntList;
    }
}
