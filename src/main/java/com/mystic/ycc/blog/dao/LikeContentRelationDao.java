package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.LikeContentRelation;

import java.util.List;

public interface LikeContentRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeContentRelation record);

    int insertSelective(LikeContentRelation record);

    LikeContentRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeContentRelation record);

    int updateByPrimaryKey(LikeContentRelation record);

    List<Integer> getCircleLikeList(Integer currentId);
}