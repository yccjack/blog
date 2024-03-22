package com.mystic.ycc.blog.bean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class CircleContentVo {

    static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 文章ID
     */
    private Integer id;
    /**
     * 文章内容
     */

    private String content;

    /**
     * 文章标题
     */
    private String contentTitle;
    /**
     * 文章图片名称集合
     */
    private String contentpic;

    /**
     * 文章图片名称集合
     */
    private List<String> pics;

    /**
     * 回复数量
     */
    private Integer talk;
    /**
     * 用户id
     */
    private Integer userid;
    /**
     * 点赞数量
     */
    private Integer likecount;
    /**
     * 图片id集合
     */
    private String imgid;
    /**
     * 文章topic类型 默认5
     */
    private Integer type;
    /**
     * 回复楼层数
     */
    private Integer floor;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 作者头像
     */
    private String avatar;
    /**
     * 作者昵称
     */
    private String nickname;
    /**
     * 作者等级简介
     */
    private String leverTitle;
    /**
     * 作者等级
     */
    private Integer lever;
    /**
     * 用户id
     */
    private String avatar_id;
    /**
     * 当前用户对文章是否点赞
     */
    private String youLiked;
    /**
     * 文章所属主题名称
     */
    private String introduction;


    public void setContentpic(String contentpic) {
        this.contentpic = contentpic;
        if (StringUtils.isNotEmpty(contentpic)) {
            this.pics = Arrays.asList(contentpic.split(","));
        }
    }


    public void setCreate_time(Date create_time) {
        this.create_time = sf.format(create_time);
    }
}
