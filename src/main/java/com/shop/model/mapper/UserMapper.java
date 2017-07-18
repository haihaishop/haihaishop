package com.shop.model.mapper;

import com.shop.model.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 18240 on 2017/7/17.
 */
public interface UserMapper {
    //使用@Select注解指明getById方法要执行的SQL
    @Insert("insert into user(login_name,password,email,phone,role) " +
            "values (#{login_name},#{password},#{email},#{phone},#{role})")
    public void add(User user);

    @Select("select * from user where login_name=#{login_name}")
    public User getUserFromLoginName(@Param("loginName")String loginName);
    public void addUser(User user);

    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from user")
    public List<User> getAll();
    public void add(User user);

    @Select("select * from user where login_name=#{login_name}")
    public User getUserFromLoginName(@Param("loginName")String loginName);
}
