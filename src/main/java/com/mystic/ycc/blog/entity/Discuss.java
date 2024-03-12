package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * discuss
 * @author 
 */
@Data
public class Discuss implements Serializable {
    private Integer id;

    private String discussContent;

    private Integer contentId;

    /**
     * 楼层
     */
    private Integer floor=0;

    private Integer userId;

    /**
     * 点赞数
     */
    private Integer likecount=0;

    private Date createTime;

    /**
     * 图片id
     */
    private String imgs;

    private static final long serialVersionUID = 1L;
}