package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * circle_content
 *
 * @author
 */
@Data
public class CircleContent implements Serializable {
    private Integer id;

    private String content;

    private String contenttitle;

    private String contentpic;

    private Integer talk = 0;

    private Integer userid;

    private Integer likecount = 0;

    private String imgid;

    private Integer type = 5;

    private Integer floor = 0;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public void incrementTalk() {
        if (this.talk != null) {
            this.talk += 1;
        } else {
            this.talk = 1;
        }
    }

    public void incrementFloor() {
        if (this.floor != null) {
            this.floor += 1;
        } else {
            this.floor = 1;
        }
    }
}