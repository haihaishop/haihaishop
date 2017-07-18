package com.shop.model.service;

/**
 * Created by 15764 on 2017-07-18.
 */
public interface UserManagerInterface {
    public static String cacheName = "userCache";
    public boolean hasUser(String loginName);

}
