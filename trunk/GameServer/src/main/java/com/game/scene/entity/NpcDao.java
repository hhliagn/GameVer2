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
public class NpcDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public NpcEnt get(long id) {
        String hql = "select p from NpcEnt p where id = ?";
        Query query = getSession().createQuery(hql).setLong(0, id);
        NpcEnt npcEnt = (NpcEnt) query.uniqueResult();
        return npcEnt;
    }

    //查询列表
    public List<NpcEnt> getList() {
        String hql = "FROM NpcEnt";
        Query query = getSession().createQuery(hql);
        List<NpcEnt> npcEntList = query.list();
        return npcEntList;
    }

    //新增/更新
    public void saveOrUpdate(NpcEnt npcEnt) {
        Session session = getSession();
        session.saveOrUpdate(npcEnt);
    }

    //删除
    public void delete(NpcEnt npcEnt){
        try {
            getSession().delete(npcEnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据ID查找List
    public List<NpcEnt> findByMapId(int mapId) {
        String hql = "select p from NpcEnt p where mapId = ?";
        Query query = getSession().createQuery(hql).setInteger(0, mapId);
        List<NpcEnt> npcEntList = query.list();
        return npcEntList;
    }
}
