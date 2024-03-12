package com.mystic.ycc.blog.cont;

import com.google.gson.Gson;
import com.mystic.ycc.blog.bean.*;
import com.mystic.ycc.blog.bean.save.CircleSaveVo;
import com.mystic.ycc.blog.bean.save.ReplySaveVo;
import com.mystic.ycc.blog.entity.CircleInfo;
import com.mystic.ycc.blog.entity.UserInfo;
import com.mystic.ycc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("blog")
public class UserController {


    Gson gson = new Gson();

    @Autowired
    private UserService userService;


    @GetMapping("/getUserTopic")
    public String getUserTopic(HttpServletRequest request, Integer userId) {
        String user_id = request.getHeader("user_id");
        Integer currentUser = null;
        if (user_id == null) {
            currentUser = userId;
        } else {
            currentUser = Integer.parseInt(user_id);
        }
        List<CircleInfo> userTopicCircleInfos = userService.getUserTopic(currentUser);

        return gson.toJson(userTopicCircleInfos);
    }


    @GetMapping("/getMsgInfo")
    public String getMsgInfo(HttpServletRequest request, Integer type) {
        String user_id = request.getHeader("user_id");
        if (user_id == null) {
            return null;
        }
        Integer currentUser = Integer.parseInt(user_id);
        List<UserMsgDetails> msgInfo = userService.getMsgInfo(currentUser, type);

        return gson.toJson(msgInfo);
    }


    @GetMapping("/getUserContent")
    public String getUserContent(Integer user_id) {
        List<CircleContentVo> circleContents = userService.getUserContent(user_id);
        return gson.toJson(circleContents);
    }

    @GetMapping("/getUserMsg")
    public String getUserMsg(Integer user_id) {
        UserMsgVo userMsgVo = userService.getUserMsg(user_id);
        return gson.toJson(userMsgVo);
    }

    @GetMapping("/getReply")
    public String getReply(Integer discuss_id,Integer limit) {

        List<DiscussReplyVo> discussReplyVos = userService.getReply(discuss_id,limit);
        return gson.toJson(discussReplyVos);
    }

    @GetMapping("/getDiscuss")
    public String getDiscuss(Integer content_id) {

        List<DiscussVo> discussVos = userService.getDiscuss(content_id);
        return gson.toJson(discussVos);
    }

    @GetMapping("/readMsg")
    public String readMsg(HttpServletRequest request, Integer id) {
        String user_id = request.getHeader("user_id");
        if (user_id == null) {
            return "success";
        }
        Integer currentUser = Integer.parseInt(user_id);
        userService.readMsg(currentUser, id);
        return "success";
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(HttpServletRequest request, Integer id) {
        UserInfo userInfo = userService.getUserInfo(id);
        return gson.toJson(userInfo);
    }



}
