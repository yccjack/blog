package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_msg
 * @author 
 */
@Data
public class UserMsg implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer fromUserId;

    private String fromUsername;

    /**
     * 0:系统通知，1：文章回复我的，2：手动的赞，3：@我的
4：讨论回复我的
     */
    private Integer msgType=0;

    /**
     * 0:未读，1：已读
     */
    private Integer isRead=0;

    private Date createTime=new Date();

    private Integer contentId;

    private Integer discussId;

    private Integer replyId;

    private static final long serialVersionUID = 1L;
}