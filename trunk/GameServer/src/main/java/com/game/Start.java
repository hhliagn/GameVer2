package com.game;

import com.game.netty.server.NettyServer;
import com.game.scene.service.MapService;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

    private static ClassPathXmlApplicationContext applicationContext;

    public static void main(String[] args) {
        autoCreateTable();
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringContext.getBean("globalService",GlobalService.class).writeData();
        SpringContext.getBean("mapService", MapService.class).initMapData();
        //netty server
        new Thread(new Runnable() {
            @Override
            public void run() {
                NettyServer nettyServer = new NettyServer();
            }
        }).start();
    }

    private static void autoCreateTable() {
        Configuration cfg = new Configuration().configure();
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);
    }
}
