package com.game.entity.account;

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
public class AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Account get(String accountId){
        try {
            Session session = getSession();
            String hql = "select a from Account a where accountId = ?";
            Query query = session.createQuery(hql).setString(0, accountId);
            Account account = (Account) query.uniqueResult();
            return account;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Account> getList(){
        try {
            Session session = getSession();
            String hql = "from Account";
            Query query = session.createQuery(hql);
            List<Account> accountList = query.list();
            return accountList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Account account){
        Session session = getSession();
        session.saveOrUpdate(account);
    }

    //删除
    public void delete(Account account){
        try {
            Session session = getSession();
            session.delete(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getLoginAccount(String accountId, String password) {
        try {
            session = getSession();
            String hql = "select a from Account a where accountId = ? and password = ?";
            Query query = session.createQuery(hql).setString(0, accountId).setString(1, password);
            Account account = (Account) query.uniqueResult();
            return account;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
