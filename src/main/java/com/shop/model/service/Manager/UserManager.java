package com.shop.model.service.Manager;

import com.shop.model.mapper.UserMapper;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 15764 on 2017-07-18.
 */
@Service("userManager")
@Transactional
@CacheConfig(cacheNames = {UserManagerInterface.cacheName})
public class UserManager implements UserManagerInterface{
    @Autowired
    UserMapper userMapper;

    public boolean hasUser(String loginName) {
        if (userMapper.getUserFromLoginName(loginName) == null)
            return true;
        else
            return false;
    }
}
