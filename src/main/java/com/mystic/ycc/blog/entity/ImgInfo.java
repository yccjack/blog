package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * img_info
 * @author 
 */
@Data
public class ImgInfo implements Serializable {
    private Integer id;

    private String filename;

    private String filepath;

    private String imagesize;

    private String imagelen;

    private String site;

    /**
     * 用户封面图
     */
    private String userTopiPic;

    private static final long serialVersionUID = 1L;
}