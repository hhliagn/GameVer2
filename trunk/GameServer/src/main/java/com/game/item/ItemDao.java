package com.game.item;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Item get(long id){
        try {
            Session session = getSession();
            String hql = "select r from Item r where id = ?";
            Query query = session.createQuery(hql).setLong(0, id);
            Item item = (Item) query.uniqueResult();
            item.doDeserialize();
            return item;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Item> getList(){
        try {
            Session session = getSession();
            String hql = "from Item";
            Query query = session.createQuery(hql);
            List<Item> itemList = query.list();
            for (Item item : itemList) {
                item.doDeserialize();
            }
            return itemList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Item item){
        item.doSerialize();
        Session session = getSession();
        session.saveOrUpdate(item);
    }

    //删除
    public void delete(Item item){
        try {
            item.doSerialize();
            Session session = getSession();
            session.delete(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
