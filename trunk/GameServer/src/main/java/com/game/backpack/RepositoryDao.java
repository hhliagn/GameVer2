package com.game.backpack;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RepositoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Repository get(String accountId){
        try {
            Session session = getSession();
            String hql = "select r from Repository r where accountId = ?";
            Query query = session.createQuery(hql).setString(0, accountId);
            Repository repository = (Repository) query.uniqueResult();
            repository.doDeserialize();
            return repository;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Repository> getList(){
        try {
            Session session = getSession();
            String hql = "from Repository";
            Query query = session.createQuery(hql);
            List<Repository> RepositoryList = query.list();
            return RepositoryList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Repository repository){
        repository.doSerialize();
        Session session = getSession();
        session.saveOrUpdate(repository);
    }

    //删除
    public void delete(Repository repository){
        try {
            Session session = getSession();
            session.delete(repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
