package com.mystic.ycc.blog.bean;

import lombok.Data;

@Data
public class UserMsgVo {

    /**
     * 系统消息
     */
    private Integer system;
    /**
     * 回复
     */
    private Integer reply;
    /**
     * @
     */
    private Integer aite;
    /**
     * 点赞
     */
    private Integer likeMe;
    /**
     * 总共
     */
    private Integer total;
}
