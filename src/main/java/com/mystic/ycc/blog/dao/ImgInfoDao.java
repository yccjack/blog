package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.entity.ImgInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImgInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ImgInfo record);

    int insertSelective(ImgInfo record);

    ImgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImgInfo record);

    int updateByPrimaryKey(ImgInfo record);

    List<ImgInfo> getPic(List<Integer> ids);

    List<String> getImgPathFromIds(@Param("ids") List<String> ids);
}