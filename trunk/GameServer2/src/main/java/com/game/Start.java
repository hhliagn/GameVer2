package com.game;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("applicationContext.xml");

        //自动创表
        //读取配置文件hibernate.cfg.xml
        Configuration cfg = new Configuration().configure();
        //创建SchemeExport实例
        SchemaExport sExport = new SchemaExport(cfg);
        //创建数据库表
        sExport.create(true, true);


    }
}
