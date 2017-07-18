package com.shop.model.mapper;

import com.shop.model.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 18240 on 2017/7/17.
 */
public interface UserMapper {
    @Insert("insert into user(login_name,password,email,phone,role) " +
            "values (#{login_name},#{password},#{email},#{phone},#{role})")
    public void add(User user);
}
