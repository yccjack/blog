package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo getUserInfoByNameAndPwd(@Param("username") String username, @Param("password") String password);

    int hasSameNameUser(String username);
}