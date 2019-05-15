package com.game.Item;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DrugDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Drug get(int id){
        try {
            Session session = getSession();
            String hql = "select d from Drug d where id = ?";
            Query query = session.createQuery(hql).setInteger(0, id);
            Drug Drug = (Drug) query.uniqueResult();
            return Drug;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Drug> getList(){
        try {
            Session session = getSession();
            String hql = "from Drug";
            Query query = session.createQuery(hql);
            List<Drug> DrugList = query.list();
            return DrugList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Drug drug){
        Session session = getSession();
        session.saveOrUpdate(drug);
    }

    //删除
    public void delete(Drug drug){
        try {
            Session session = getSession();
            session.delete(drug);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
