package com.shop.model.service.Manager;

import com.shop.model.domain.User;
import com.shop.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 18240 on 2017/7/19.
 */

@Service("userManager")
@Transactional
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String usernamw) throws UsernameNotFoundException {
        return user;
    }
}
