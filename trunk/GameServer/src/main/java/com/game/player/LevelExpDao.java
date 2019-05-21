package com.game.player;

import com.game.player.LevelExp;
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
public class LevelExpDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public LevelExp get(int level){
        try {
            Session session = getSession();
            String hql = "select p from LevelExp p where level = ?";
            Query query = session.createQuery(hql).setInteger(0, level);
            LevelExp levelExp = (LevelExp) query.uniqueResult();
            return levelExp;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<LevelExp> getList(){
        try {
            Session session = getSession();
            String hql = "from LevelExp";
            Query query = session.createQuery(hql);
            List<LevelExp> levelExpList = query.list();
            return levelExpList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(LevelExp levelExp){
        try {
            Session session = getSession();
            session.saveOrUpdate(levelExp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除
    public void delete(LevelExp levelExp){
        try {
            Session session = getSession();
            session.delete(levelExp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取低于该经验值的最大等级, id逆序取第一条记录
    public LevelExp getMaxLevel(long exp){
        Session session = getSession();
        String hql = "select a from LevelExp a where exp < ? order by id DESC";
        Query query = session.createQuery(hql).setLong(0, exp);
        List<LevelExp> levelExpList = query.list();
        LevelExp levelExp = levelExpList.get(0);
        return levelExp;
    }
}
