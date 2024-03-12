package com.mystic.ycc.blog.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DiscussVo {

    private Integer id;

    /**
     * 发起讨论的内容
     */
    private String discussContent;

    /**
     * 发起讨论关联的文章id
     */
    private Integer contentId;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 发起讨论的用户id
     */
    private Integer userId;

    /**
     * 点赞数
     */
    private Integer likecount;

    private Date createTime;

    /**
     * 图片id
     */
    private String imgs;
    /**
     * 发起讨论的用户头像
     */
    private String avatar;
    /**
     * 发起讨论的用户昵称
     */
    private String nickname;
    /**
     * 发起讨论的用户名
     */
    private String name;
    /**
     * 发起讨论的用户等级简介
     */
    private String leverTitle;
    /**
     * 发起讨论的用户等级
     */
    private Integer lever;

    private Integer avatar_id;
    /**
     * 发起讨论内容图片
     */
    private List<String> pics;
    /**
     * 发起讨论得到的总回复数
     */
    private Integer totalReply;
    /**
     * 发起讨论的回复
     */
    private List<DiscussReplyVo> replyList;
}
