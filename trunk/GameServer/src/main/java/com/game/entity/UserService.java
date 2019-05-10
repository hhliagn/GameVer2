package com.game.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public void findUserById(){
        userDao.findUserById();
    }
}
