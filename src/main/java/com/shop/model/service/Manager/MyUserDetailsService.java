package com.shop.model.service.Manager;

import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.mapper.RoleMapper;
import com.shop.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 18240 on 2017/7/19.
 */

public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoggingUtil.log(username);
        User user = userMapper.getUserByLoginName(username);
        if(user==null){
            throw new UsernameNotFoundException(username + "not found");
        }else{
            return buildUserDetail(user);
        }
    }

    private org.springframework.security.core.userdetails.User buildUserDetail(User user){
        List<GrantedAuthority> authorities=buildUserAuthorities(user);
        LoggingUtil.log(authorities);
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    private List<GrantedAuthority> buildUserAuthorities(User user){
        String roleName=roleMapper.getNameFromRoleID(user.getRole_id());
        Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(roleName));
        return new ArrayList<GrantedAuthority>(authorities);
    }
}
