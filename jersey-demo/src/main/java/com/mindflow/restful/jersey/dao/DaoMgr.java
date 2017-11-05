package com.mindflow.restful.jersey.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindflow.restful.jersey.domain.User;

import java.util.List;


@Repository
public class DaoMgr {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public  List<User> findAll() {
        String sql = "select * from user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
    public void add(User user) {
        String sql = "insert into user (first_name, age) values (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge());
    }

}