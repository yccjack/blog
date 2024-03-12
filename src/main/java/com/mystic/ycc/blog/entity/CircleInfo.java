package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * circle_info
 * @author 
 */
@Data
public class CircleInfo implements Serializable {
    private Integer id;

    private String name;

    private String avatar;

    private String introduction;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}