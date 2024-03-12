package com.mystic.ycc.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;

    private String name;

    private Integer lever=1;

    private String levertitle="cainiao";

    private String avatar="http://e.gschaos.club/pyimg/16.png";

    private Integer topiccount=0;

    private Integer comment=0;

    private Integer focuson=0;

    private Integer fan=0;

    private Integer leverid=-1;

    private String email;

    private String password;

    private String nickname="wu";

    private Date createTime;

    private String sign;

    /**
     * 用户封面图
     */
    private String userTopPic="http://e.gschaos.club/pyimg/16.png";

    private static final long serialVersionUID = 1L;
}