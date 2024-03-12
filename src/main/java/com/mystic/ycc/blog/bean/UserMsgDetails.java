package com.mystic.ycc.blog.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserMsgDetails {

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 来源用户id
     */
    private Integer fromUserId;
    /**
     * 来源用户名称
     */

    private String fromUsername;

    /**
     * 0:系统通知，1：文章回复我的，2：手动的赞，3：@我的
     4：讨论回复我的
     */
    private Integer msgType;

    /**
     * 0:未读，1：已读
     */
    private Integer isRead;
    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 内容id
     */
    private Integer contentId;

    /**
     * 讨论id
     */
    private Integer discussId;

    /**
     * 回复id
     */
    private Integer replyId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 内容标题
     */
    private String contentTitle;
}
