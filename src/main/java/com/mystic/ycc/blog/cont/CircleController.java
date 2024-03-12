package com.mystic.ycc.blog.cont;

import com.google.gson.Gson;
import com.mystic.ycc.blog.bean.save.CircleContentSaveVo;
import com.mystic.ycc.blog.bean.CircleContentVo;
import com.mystic.ycc.blog.bean.save.CircleSaveVo;
import com.mystic.ycc.blog.bean.save.DiscussSaveVo;
import com.mystic.ycc.blog.bean.save.ReplySaveVo;
import com.mystic.ycc.blog.entity.CircleInfo;
import com.mystic.ycc.blog.service.BlogHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("blog")
public class CircleController {

    Gson gson = new Gson();

    @Autowired
    private BlogHomeService blogHomeService;

    @GetMapping("/getCircleContent")
    public String getCircleContent(Integer type) {
        List<CircleContentVo> circleContents = blogHomeService.getCircleContent(type);
        return gson.toJson(circleContents);
    }


    @GetMapping("/getCircleDetail")
    public String getCircleDetail(HttpServletRequest request, Integer id) {
        String user_id = request.getHeader("user_id");
        Integer userId = -1;
        if (user_id != null) {
            userId = Integer.valueOf(user_id);
        }
        CircleContentVo circleContents = blogHomeService.getCircleDetail(id, userId);
        return gson.toJson(circleContents);
    }


    @GetMapping("/getCircleLikeList")
    public String getCircleLikeList(HttpServletRequest request, @RequestParam(required = false) Integer user_id) {
        String userId = request.getHeader("user_id");
        Integer currentId = null;
        if (user_id == null) {
            currentId = Integer.valueOf(userId);
        }
        List<Integer> circleLikeList = blogHomeService.getCircleLikeList(currentId);
        return gson.toJson(circleLikeList);
    }

    @GetMapping("/circleInfo")
    public String circleInfo() {

        List<CircleInfo> circleLikeList = blogHomeService.circleInfo();
        return gson.toJson(circleLikeList);
    }


    @RequestMapping("/saveCircleContent")
    public String saveCircleContent(HttpServletRequest request, CircleContentSaveVo circleContentSaveVo) {
        String userId = request.getHeader("user_id");
        Integer currentId = null;
        if (userId != null) {
            currentId = Integer.valueOf(userId);
        } else {
            return null;
        }
        blogHomeService.saveCircleContent(currentId, circleContentSaveVo);
        return gson.toJson("success");
    }


    @RequestMapping("/saveDiscuss")
    public String saveDiscuss(HttpServletRequest request, DiscussSaveVo discussSaveVo) {
        String userId = request.getHeader("user_id");
        Integer currentId = null;
        if (userId != null) {
            currentId = Integer.valueOf(userId);
        } else {
            return null;
        }
        blogHomeService.saveDiscuss(currentId, discussSaveVo);
        return gson.toJson("success");
    }


    @RequestMapping("/createCircle")
    public String createCircle(HttpServletRequest request, CircleSaveVo circleSaveVo) {
        String userId = request.getHeader("user_id");
        Integer currentId = null;
        if (userId != null) {
            currentId = Integer.valueOf(userId);
        } else {
            return null;
        }
        blogHomeService.createCircle(currentId, circleSaveVo);
        return gson.toJson("success");
    }

    @RequestMapping("/saveReply")
    public String saveReply(HttpServletRequest request, ReplySaveVo replySaveVo) {
        String userId = request.getHeader("user_id");
        Integer currentId;
        if (userId != null) {
            currentId = Integer.valueOf(userId);
        } else {
            return null;
        }
        blogHomeService.saveReply(currentId, replySaveVo);
        return gson.toJson("success");
    }
}
