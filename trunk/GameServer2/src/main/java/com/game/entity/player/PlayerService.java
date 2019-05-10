package com.game.entity.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

}
