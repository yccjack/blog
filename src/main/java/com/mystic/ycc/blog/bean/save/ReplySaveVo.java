package com.mystic.ycc.blog.bean.save;

import lombok.Data;

/**
 * 评论回复实体
 */
@Data
public class ReplySaveVo {
    /**
     * 回复内容
     */
    private String replyContent;
    /**
     * 回复包含的图片
     */
    private String pics;
    /**
     * 回复关联的文章id
     */
    private Integer content_id;
    /**
     * 回复关联的评论id
     */
    private Integer discuss_id;
}
