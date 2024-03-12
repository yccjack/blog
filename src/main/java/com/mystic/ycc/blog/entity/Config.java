package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * config
 * @author 
 */
@Data
public class Config implements Serializable {
    private Integer id;

    private String content;

    /**
     * 1: 圈子tips，左上角
2：用户圈子信息(加入的，创建的，我的话题)
3：发布圈子的类型
4：推荐圈子
5：公告
6：菜单

     */
    private Integer type;

    private String typename;

    private String avatar;

    /**
     * 对应的内容id
     */
    private Integer contentId;

    private static final long serialVersionUID = 1L;
}