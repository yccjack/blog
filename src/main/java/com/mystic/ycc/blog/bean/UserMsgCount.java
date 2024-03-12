package com.mystic.ycc.blog.bean;

import lombok.Data;

@Data
public class UserMsgCount {
    /**
     * 不同类型总量
     */
    private Integer count;
    /**
     * 消息类型 {@link UserMsgVo}
     */
    private Integer type;
}
