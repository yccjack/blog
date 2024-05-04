package com.mystic.ycc.blog.bean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Data
public class DiscussReplyVo {

    private Integer id;

    private String reply_content;

    private Integer discussId;

    private Integer userId;

    /**
     * 点赞数
     */
    private Integer subLike;

    private Date createTime;

    private Integer toUser;

    private String toUsername;


    /**
     * 图片id
     */
    private String imgs;
    private String avatar;
    private String nickname;
    private String name;
    private String leverTitle;
    private Integer lever;
    private Integer avatar_id;

    private List<String> pics;


    public void setImgs(String imgs) {
        this.imgs = imgs;
        if(!StringUtils.isBlank(imgs)) {
            pics = Arrays.asList(imgs.split(","));
        }
    }
}
