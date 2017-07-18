package com.shop.model.service;

import com.shop.model.domain.User;

/**
 * Created by 18240 on 2017/7/18.
 */
public interface UserManager {
    public static final String cacheName="userCache";
    public void addUser(User user);
    public void userLogin(User user);
}
