package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from users where username=#{username} and password=#{password}")
    User getUser(@Param("username") String username, @Param("password") String password);
    @Select("select * from users where username=#{username}")
    User getUserByName(@Param("username") String username);
    @Select("select * from users ")
    List<User> getAllUser();
    @Insert("insert into users(username,password) values(#{username},#{password})")
    void insertUser(@Param("username") String username, @Param("password") String password);

}
