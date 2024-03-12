package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * discuss_reply
 * @author 
 */
@Data
public class DiscussReply implements Serializable {
    private Integer id;

    private String replyContent;

    private Integer discussId;

    private Integer userId;

    /**
     * 点赞数
     */
    private Integer subLike=0;

    private Date createTime;

    private Integer toUser;

    private String toUsername;

    private String imgs;

    private static final long serialVersionUID = 1L;
}