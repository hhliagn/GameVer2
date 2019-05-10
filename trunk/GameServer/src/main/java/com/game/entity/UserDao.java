package com.game.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void findUserById() {
        String sql = "select * from User where id = ?";

        //queryForObject
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{2}, new BeanPropertyRowMapper<User>(User.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        System.out.println(user);

        //queryForList
//        List<User> query = jdbcTemplate.query(sql, new Object[]{2}, new BeanPropertyRowMapper<User>(User.class));
//        System.out.println(query);

        //queryForMap
//        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, new Object[]{1});

        //update
//        String sql2 = "update user set name = ? where id = ?";
//        int cc = jdbcTemplate.update(sql2, "cc", 1);
//        System.out.println(cc);
    }
}
