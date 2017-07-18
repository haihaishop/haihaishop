package com.shop.model.service.Manager;


import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.mapper.UserMapper;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 18240 on 2017/7/18.
 */
@Service("userManager")
@Transactional
@CacheConfig(cacheNames = {UserManagerInterface.cacheName})
public class UserManager implements UserManagerInterface {
    @Autowired
    private UserMapper userMapper;

    @CacheEvict(allEntries = true)
    public void addUser(User user) {
        userMapper.addUser(user);
    }

//    @Cacheable(key = "#root.methodName+#root.args[0]")
    public boolean hasUser(String loginName) {
        User user = userMapper.getUserByLoginName(loginName);
        return user != null;
    }
}