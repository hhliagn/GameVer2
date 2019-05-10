package com.game;

import com.game.entity.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static SpringContext instance;

//    @PostConstruct
//    private void init(){
//        instance = this;
//    }
//
//    @Autowired
//    private UserService userService;
//
//    public static UserService getUserService(){
//        return instance.userService;
//    }

    public static <T> T getBean(String name,Class<T> tClass){
        return (T) applicationContext.getBean(name);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }
}
