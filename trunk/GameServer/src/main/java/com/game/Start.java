package com.game;

import com.game.entity.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = SpringContext.getBean("userService",UserService.class);
        userService.findUserById();
    }
}
