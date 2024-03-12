package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * like_content_relation
 * @author 
 */
@Data
public class LikeContentRelation implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer contentId;

    private Integer userLike;

    private static final long serialVersionUID = 1L;
}