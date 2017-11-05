package com.mindflow.restful.jersey.service;

import com.mindflow.restful.jersey.domain.User;

import java.util.List;


public interface IUserService {

    long insert(User user);

    List<User> getUserList();

    User findUserById(long id);

    User findUserByName(String username);

    int update(User user);

    int delete();   //全部删除

    int deleteById(long id);
}
