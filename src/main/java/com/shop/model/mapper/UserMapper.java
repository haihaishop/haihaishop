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

    @Insert("insert into user(login_name,password,email,phone,role_id) " +
            "values (#{login_name},#{password},#{email},#{phone},#{role_id})")
   public void addUser(User user);

    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from user")
    public List<User> getAllUser();

    @Select("select * from user where login_name=#{login_name}")
    public User getUserByLoginName(@Param("login_name")String loginName);

