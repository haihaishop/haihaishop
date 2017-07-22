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

    @Insert("insert into user(username,password,email,phone,role_id,create_date) " +
            "values (#{username},#{password},#{email},#{phone},#{role_id},#{create_date})")
    void addUser(User user);

    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where username=#{username}")
    User getUserByLoginName(@Param("username") String username);

    @Select("select * from user where role_id=#{role_id}")
    List<User> getUsersByRoleId(@Param("role_id") Long role_id);

    @Delete("delete from user where username=#{username}")
    void deleteUserByLoginName(@Param("username") String username);

    @Select("select * from user where username=#{username}")
    User loginUser(@Param("username") String username);

    @Update("update user set password=#{password} where username=#{username}")
    void changePasswordByUsername(@Param("username") String username,
                                  @Param("password") String password);

    @Select("select password from user where username=#{username}")
    String getPasswordByUsername(@Param("username") String username);

    @Select("select role_id from user where username = #{username}")
    int getRoleIdByUsername(@Param("username") String username);

    @Update("update user set nick_name = #{nick_name}, email = #{email}, phone = #{phone}, sex=#{sex} where username = #{username}")
    void changeInformationByUsername(User user);

    @Update("update user set name = #{name}, id_card = #{id_card}, image = #{image} where username = #{username}")
    void authentication(User user);

}