package com.game.player;

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
public class PlayerDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public Player get(long id){
        try {
            Session session = getSession();
            String hql = "select p from Player p where id = ?";
            Query query = session.createQuery(hql).setLong(0, id);
            Player player = (Player) query.uniqueResult();
            player.doDeserialize();
            return player;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询列表
    public List<Player> getList(){
        try {
            Session session = getSession();
            String hql = "from Player";
            Query query = session.createQuery(hql);
            List<Player> playerList = query.list();
            for (Player player : playerList) {
                player.doDeserialize();
            }
            return playerList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加/更新
    public void saveOrUpdate(Player player){
        try {
            player.doSerialize();
            Session session = getSession();
            session.saveOrUpdate(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除
    public void delete(Player player){
        try {
            player.doSerialize();
            Session session = getSession();
            session.delete(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取最后一条记录
    public Long getLast() {
        Session session = getSession();
        String hql = "select p from Player p order by id DESC";
        Query query = session.createQuery(hql);
        List<Player> players = query.list();
        Player player = players.get(0);
        if (player == null){
            return 0L;
        }
        long id = player.getId();
        return id;
    }

    //根据accountId查询玩家列表
    public List<Player> getPlayers(String accountId) {
        try {
            Session session = getSession();
            String hql = "select p from Player p where accountId = ?";
            Query query = session.createQuery(hql).setString(0, accountId);
            List<Player> players = query.list();
            for (Player player : players) {
                player.doDeserialize();
            }
            return players;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
