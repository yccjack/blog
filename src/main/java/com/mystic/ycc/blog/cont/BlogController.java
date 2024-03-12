package com.mystic.ycc.blog.cont;

import com.google.gson.Gson;
import com.mystic.ycc.blog.bean.CircleContentVo;
import com.mystic.ycc.blog.bean.LoginUser;
import com.mystic.ycc.blog.entity.CircleInfo;
import com.mystic.ycc.blog.entity.ImgInfo;
import com.mystic.ycc.blog.entity.UserInfo;
import com.mystic.ycc.blog.service.AuthenticationService;
import com.mystic.ycc.blog.service.BlogHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blog")
@CrossOrigin
public class BlogController {
    Gson gson = new Gson();

    @Autowired
    private BlogHomeService blogHomeService;

    @Autowired
    AuthenticationService authenticationService;


    @GetMapping("/getTopicDetail")
    public String getTopicDetail(Integer id) {
        List<CircleContentVo> circleContents = blogHomeService.getTopicDetail(id);
        return gson.toJson(circleContents);
    }

    @GetMapping("/getCurrentTopicInfo")
    public String getCurrentTopicInfo(Integer topic) {
        CircleInfo circleInfo = blogHomeService.getCurrentTopicInfo(topic);
        return gson.toJson(circleInfo);
    }

    @GetMapping("/getDefaultCircle")
    public String getDefaultCircle() {
        CircleInfo circleInfo = blogHomeService.getDefaultCircle();
        return gson.toJson(circleInfo);
    }

    @RequestMapping("/getPic")
    public String getPic(String id) {
        if (id != null) {
            String[] split = id.split(",");
            List<Integer> ids = new ArrayList<>(split.length);
            for (String s : split) {
                ids.add(Integer.valueOf(s));
            }
            List<ImgInfo> imgInfos = blogHomeService.getPic(ids);
            return gson.toJson(imgInfos);
        } else {
            return null;
        }

    }

    /**
     * 注册登录
     *
     * @param loginUser {@link LoginUser}
     * @param type      0: 登录 1:注册
     * @return
     */
    @RequestMapping("/loginOrRegister")
    public String loginOrRegister(LoginUser loginUser, Integer type) {
        UserInfo userInfo = null;
        if (type == 0) {
            userInfo = authenticationService.login(loginUser);
        } else if (type == 1) {
            userInfo = authenticationService.registry(loginUser);
        }
        if (userInfo != null) {
            return gson.toJson(userInfo);
        }
        return null;
    }

}
