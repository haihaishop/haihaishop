package com.shop.model.service.Manager;


import com.shop.Utils.BCryptUtil;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.mapper.RoleMapper;
import com.shop.model.mapper.UserMapper;
import com.shop.model.service.UserManagerInterface;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18240 on 2017/7/18.
 */
@Service("userService")
@Transactional
@CacheConfig(cacheNames = {UserManagerInterface.cacheName})
public class UserService implements UserManagerInterface {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @CacheEvict(allEntries = true)
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public boolean hasUser(String loginName) {
        User user = userMapper.getUserByLoginName(loginName);
        return user != null;
    }

    public User loginUser(String username) {
        User user = userMapper.loginUser(username);
        return user;
    }

    @Cacheable(key = "#root.methodName")
    public List<User> getAllUser() {
        List<User> userList=userMapper.getAllUser();
        return userList;
    }

    @Cacheable(key = "#root.methodName")
    public List<User> getAllAdmins() {
        Long role_id = roleMapper.getRoleIdFromName("ROLE_ADMIN");
        LoggingUtil.log(role_id);
        return userMapper.getUsersByRoleId(role_id);
    }

    @CacheEvict(allEntries = true)
    public void deleteUserByLoginName(String username) {
        userMapper.deleteUserByLoginName(username);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]+#root.args[1]")
    public boolean authUser(String username, String password) {
        String oldPassword = userMapper.getPasswordByUsername(username);
        return BCryptUtil.match(oldPassword, password);
    }

    @CacheEvict(allEntries = true)
    public void changePasswordByUsername(String username, String password) {
        String passwordBCrypt = BCryptUtil.encode(password);
        userMapper.changePasswordByUsername(username, passwordBCrypt);
    }

    @CacheEvict(allEntries = true)
    public void changeInformationByUsername(User user) {
        userMapper.changeInformationByUsername(user);
    }

    public void authentication(User user) {
        userMapper.authentication(user);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public int getRoleIdByUsername(String username) {
        return userMapper.getRoleIdByUsername(username);
    }

    public User getUserByLoginName(String username) {
        return userMapper.getUserByLoginName(username);
    }

    @CacheEvict(allEntries = true)
    public void createStore(String username) {
        userMapper.createStore(username);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }
}