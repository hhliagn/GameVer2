package com.game.backpack;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BackpackDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Backpack get(String accountId){
        try {
            Session session = getSession();
            String hql = "select b from Backpack b where accountId = ?";
            Query query = session.createQuery(hql).setString(0, accountId);
            Backpack backpack = (Backpack) query.uniqueResult();
            backpack.doDeserialize();
            return backpack;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Backpack> getList(){
        try {
            Session session = getSession();
            String hql = "from Backpack";
            Query query = session.createQuery(hql);
            List<Backpack> BackpackList = query.list();
            return BackpackList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Backpack backpack){
        backpack.doSerialize();
        Session session = getSession();
        session.saveOrUpdate(backpack);
    }

    //删除
    public void delete(Backpack backpack){
        try {
            Session session = getSession();
            session.delete(backpack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
