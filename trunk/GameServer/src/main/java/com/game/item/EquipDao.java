package com.game.item;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Equip get(int id){
        try {
            Session session = getSession();
            String hql = "select e from Equip e where id = ?";
            Query query = session.createQuery(hql).setInteger(0, id);
            Equip Equip = (Equip) query.uniqueResult();
            return Equip;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Equip> getList(){
        try {
            Session session = getSession();
            String hql = "from Equip";
            Query query = session.createQuery(hql);
            List<Equip> EquipList = query.list();
            return EquipList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Equip equip){
        Session session = getSession();
        session.saveOrUpdate(equip);
    }

    //删除
    public void delete(Equip equip){
        try {
            Session session = getSession();
            session.delete(equip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
