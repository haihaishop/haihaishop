package com.shop.model.service;

/**
 * Created by 15764 on 2017-07-18.
 */
public interface RoleManagerInterface {
    public static String cacheName = "roleCache";
    public int getRoleIdFromName(String roleName);
}
