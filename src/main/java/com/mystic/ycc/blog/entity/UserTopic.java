package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user_topic
 * @author 
 */
@Data
public class UserTopic implements Serializable {
    private Integer id;

    private Integer userId;

    private String topicIds;

    private static final long serialVersionUID = 1L;
}