package com.shop.model.service;

import com.shop.model.domain.User;

import java.util.List;

/**
 * Created by 18240 on 2017/7/18.
 */
public interface UserManagerInterface {
    public static final String cacheName="userCache";
    public void addUser(User user);
    public List<User> getAllUser();
    public boolean hasUser(String username);
    List<User> getAllAdmins();
    void deleteUserByLoginName(String username);
    public User loginUser(String username);
    boolean authUser(String username, String password);
    void changePasswordByUsername(String username, String password);
    User getUserByLoginName(String username);
    public int getRoleIdByUsername(String username);
    void changeInformationByUsername(User user);
    void authentication(User user);
    void createStore(String username);
    User getUserById(Long userId);
}
