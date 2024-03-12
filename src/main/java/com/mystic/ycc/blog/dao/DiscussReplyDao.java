package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.DiscussReply;

public interface DiscussReplyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DiscussReply record);

    int insertSelective(DiscussReply record);

    DiscussReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiscussReply record);

    int updateByPrimaryKey(DiscussReply record);
}