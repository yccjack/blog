package com.mystic.ycc.blog.bean.save;

import lombok.Data;

@Data
public class CircleContentSaveVo {

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片ids
     */
    private String pics;
    /**
     * 类型
     */
    private Integer type;
}
