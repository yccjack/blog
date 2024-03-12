package com.mystic.ycc.blog.dao;

import com.mystic.ycc.blog.bean.ConfigVo;
import com.mystic.ycc.blog.entity.Config;

import java.util.List;

public interface ConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    List<ConfigVo> getConfig(Integer type);
}