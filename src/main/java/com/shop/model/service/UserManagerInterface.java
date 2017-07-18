package com.shop.model.service;

import com.shop.model.domain.User;

import java.util.List;

/**
 * Created by 18240 on 2017/7/18.
 */
public interface UserManagerInterface {
    public static final String cacheName="userCache";
    public void addUser(User user);
    public User getUserByLoginName(String login_name);
    public List<User> getAllUser();
    public boolean hasUser(String loginName);

}
