package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.CircleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CircleInfo record);

    int insertSelective(CircleInfo record);

    CircleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CircleInfo record);

    int updateByPrimaryKey(CircleInfo record);

    List<CircleInfo> getUserTopicCircleInfo(@Param("ids") List<String> ids);

    CircleInfo getUserTopicCircleInfoById(Integer topic);

    List<CircleInfo> circleInfo();
}