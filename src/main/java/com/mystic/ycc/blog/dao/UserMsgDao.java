package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.bean.*;
import com.mystic.ycc.blog.entity.UserMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMsgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMsg record);

    int insertSelective(UserMsg record);

    UserMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMsg record);

    int updateByPrimaryKey(UserMsg record);

    List<UserMsgCount> getUserMsgCount(Integer userId);


    List<UserMsgDetails> querySpMsgByType(@Param("currentUser") Integer currentUser, @Param("type") Integer type);

    List<UserMsgDetails> queryMsgByType(@Param("currentUser") Integer currentUser, @Param("type") Integer type);

    void updateMsgRead(@Param("currentUser") Integer currentUser, @Param("type") Integer type);

    void readMsg(Integer currentUser, Integer id);

    List<DiscussReplyVo> getReply(@Param("id") Integer id, @Param("limit") Integer limit);

    List<DiscussVo> getDiscuss(Integer contentId);

    String getUserNickname(Integer userId);
}