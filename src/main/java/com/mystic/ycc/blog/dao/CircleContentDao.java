package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.bean.CircleContentVo;
import com.mystic.ycc.blog.entity.CircleContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleContentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CircleContent record);

    int insertSelective(CircleContent record);

    CircleContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CircleContent record);

    int updateByPrimaryKey(CircleContent record);

    List<CircleContentVo> getCircleContent(Integer type);

    List<CircleContentVo> getUserContent(Integer userId);

    CircleContentVo getCircleDetail(@Param("id") Integer id, @Param("userId") Integer userId);

    List<CircleContentVo> getTopicDetail(Integer id);

    CircleContent getContentById(@Param("id") Integer id);

}