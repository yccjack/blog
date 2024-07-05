package com.mystic.ycc.blog.service;

import com.mystic.ycc.blog.bean.*;
import com.mystic.ycc.blog.config.ContentConfig;
import com.mystic.ycc.blog.dao.*;
import com.mystic.ycc.blog.entity.CircleInfo;
import com.mystic.ycc.blog.entity.UserInfo;
import com.mystic.ycc.blog.entity.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMsgDao userMsgDao;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    private UserTopicDao userTopicDao;

    @Autowired
    private CircleInfoDao circleInfoDao;

    @Autowired
    private CircleContentDao circleContentDao;

    @Autowired
    ImgService imgService;

    @Value("${user.default.pwd}")
    private String userDefaultPwd;


    public UserMsgVo getUserMsg(Integer userId) {
        List<UserMsgCount> userMsgCount = userMsgDao.getUserMsgCount(userId);
        UserMsgVo userMsgVo = new UserMsgVo();
        if (!CollectionUtils.isEmpty(userMsgCount)) {
            userMsgCount.forEach(p -> {

                switch (p.getType()) {
                    case 0:
                        userMsgVo.setSystem(p.getCount());
                        break;
                    case 1:
                        userMsgVo.setReply(p.getCount());
                        break;
                    case 2:
                        userMsgVo.setLikeMe(p.getCount());
                        break;
                    case 3:
                        userMsgVo.setAite(p.getCount());
                        break;
                }
            });
            int sum = userMsgCount.stream().mapToInt(UserMsgCount::getCount).sum();
            userMsgVo.setTotal(sum);
        }
        return userMsgVo;
    }

    public List<CircleInfo> getUserTopic(Integer currentUser) {
        String userTopic = userTopicDao.getUserTopicIds(currentUser);


        if (userTopic != null) {
            List<String> ids = Arrays.asList(userTopic.split(","));
            List<CircleInfo> circleInfos = circleInfoDao.getUserTopicCircleInfo(ids);
            return circleInfos;
        }
        return new ArrayList<>(1);
    }

    public List<CircleContentVo> getUserContent(Integer userId) {
        List<CircleContentVo> userContent = circleContentDao.getUserContent(userId);
        return userContent.stream().filter(p -> !ContentConfig.ignore_content.contains(p.getId())).collect(Collectors.toList());
    }

    public List<UserMsgDetails> getMsgInfo(Integer currentUser, Integer type) {
        List<UserMsgDetails> userMsgDetails;
        if (type == 1 || type == 4) {
            userMsgDetails = userMsgDao.querySpMsgByType(currentUser, type);
        } else {
            userMsgDetails = userMsgDao.queryMsgByType(currentUser, type);
        }
        userMsgDao.updateMsgRead(currentUser, type);
        return userMsgDetails;
    }

    public void readMsg(Integer currentUser, Integer id) {
        userMsgDao.readMsg(currentUser, id);
    }

    public List<DiscussReplyVo> getReply(Integer id, Integer limit) {
        return userMsgDao.getReply(id, limit);
    }

    public List<DiscussVo> getDiscuss(Integer contentId) {
        List<DiscussVo> discuss = userMsgDao.getDiscuss(contentId);
        if (!CollectionUtils.isEmpty(discuss)) {
            discuss.forEach(p -> {
                String imgs = p.getImgs();
                List<String> imgIds = Arrays.asList(imgs.split(","));
                List<String> imgInfos = imgService.getImgPathFromIds(imgIds);
                p.setPics(imgInfos);
                Integer id = p.getId();
                //查询此讨论的回复内容，（目前只查询最老的2条数据，可以根据计算获取最火的2条数据）
                List<DiscussReplyVo> discussReplyVos = userMsgDao.getDiscussReply(p.getId());

                if (!CollectionUtils.isEmpty(discussReplyVos)) {
                    int replyCount = userMsgDao.countDiscussReplyByDiscussId(p.getId());
                    p.setReplyList(discussReplyVos);
                    p.setTotalReply(replyCount);
                }
            });

        }
        return discuss;
    }

    public String getUserNickname(Integer userId) {
        return userMsgDao.getUserNickname(userId);
    }

    public void saveUserMsg(UserMsg userMsg) {
        userMsgDao.insert(userMsg);
    }

    public UserInfo getUserInfo(Integer id) {
        return userInfoDao.selectByPrimaryKey(id);
    }

    public UserInfo getUserInfoByNameAndPwd(String username, String password) {
        return userInfoDao.getUserInfoByNameAndPwd(username,password);
    }

    public int hasSameNameUser(String username) {
        return userInfoDao.hasSameNameUser(username);
    }

    public void saveUserInfo(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
    }
}
