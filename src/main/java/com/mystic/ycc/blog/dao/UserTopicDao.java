package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.UserTopic;

import java.util.List;

public interface UserTopicDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTopic record);

    int insertSelective(UserTopic record);

    UserTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTopic record);

    int updateByPrimaryKey(UserTopic record);

    String getUserTopicIds(Integer currentUser);

}