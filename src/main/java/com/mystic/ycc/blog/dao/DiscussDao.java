package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.Discuss;

public interface DiscussDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Discuss record);

    int insertSelective(Discuss record);

    Discuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discuss record);

    int updateByPrimaryKey(Discuss record);
}