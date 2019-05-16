package com.game.scene.entity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class MapEntDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    //查询单个
    public MapEnt get(int id){
        String hql = "select m from MapEnt m where id = ?";
        Query query = getSession().createQuery(hql).setInteger(0, id);
        MapEnt mapEnt = (MapEnt) query.uniqueResult();
        mapEnt.doDeserialize();
        return mapEnt;
    }

    //查询列表
    public List<MapEnt> getList(){
        try {
            String hql = "FROM MapEnt";
            Query query = getSession().createQuery(hql);
            List<MapEnt> list = query.list();
            for (MapEnt mapEnt : list) {
                mapEnt.doDeserialize();
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException("获取地图数据出错");
        }
    }

    //增加/更新
    public void saveOrUpdate(MapEnt mapEnt){
        mapEnt.doSerialize();
        getSession().saveOrUpdate(mapEnt);
    }

    //删除
    public void delete(MapEnt mapEnt){
        try {
            mapEnt.doSerialize();
            getSession().delete(mapEnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
