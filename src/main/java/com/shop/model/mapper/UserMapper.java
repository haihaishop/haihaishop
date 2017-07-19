package com.shop.model.mapper;

import com.shop.model.domain.User;
import org.apache.ibatis.annotations.*;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;


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
    public User getUserByLoginName(@Param("login_name") String loginName);

    @Select("select * from user where role_id=#{role_id}")
    List<User> getUsersByRoleId(@Param("role_id")Long role_id);

    @Delete("delete from user where login_name=#{login_name}")
    void deleteUserByLoginName(@Param("login_name")String loginName);
    @Select("select * from user where login_name=#{login_name} and password=#{password}")
    public User loginUser(@Param("login_name")String loginName,@Param("password")String password);
}