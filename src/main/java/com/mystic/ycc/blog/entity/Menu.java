package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * menu
 * @author 
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private String name;

    private String avatar;

    private Integer idDelete;

    private String asname;

    private static final long serialVersionUID = 1L;
}