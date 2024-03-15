package com.mystic.ycc.blog.service;

import com.alibaba.nacos.api.utils.StringUtils;
import com.mystic.ycc.blog.bean.*;
import com.mystic.ycc.blog.bean.save.CircleContentSaveVo;
import com.mystic.ycc.blog.bean.save.CircleSaveVo;
import com.mystic.ycc.blog.bean.save.DiscussSaveVo;
import com.mystic.ycc.blog.bean.save.ReplySaveVo;
import com.mystic.ycc.blog.config.ContentConfig;
import com.mystic.ycc.blog.dao.*;
import com.mystic.ycc.blog.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BlogHomeService {

    @Autowired
    private CircleContentDao circleContentDao;


    @Autowired
    private ImgService imgService;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private LikeContentRelationDao likeContentRelationDao;

    @Autowired
    private CircleInfoDao circleInfoDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussDao discussDao;

    @Autowired
    private DiscussReplyDao discussReplyDao;


    /**
     * 获取文章列表
     *
     * @param type 文章类型
     * @return
     */
    public List<CircleContentVo> getCircleContent(Integer type) {
        List<CircleContentVo> circleContent = circleContentDao.getCircleContent(type);
        return circleContent.stream().filter(p -> !ContentConfig.ignore_content.contains(p.getId())).collect(Collectors.toList());
    }


    /**
     * 获取文章内容
     *
     * @param id     文章id
     * @param userId 用户id
     * @return
     */
    public CircleContentVo getCircleDetail(Integer id, Integer userId) {
        return circleContentDao.getCircleDetail(id, userId);
    }

    /**
     * 获取主题内容
     *
     * @param id 主题id
     * @return
     */
    public List<CircleContentVo> getTopicDetail(Integer id) {
        return circleContentDao.getTopicDetail(id);
    }

    /**
     * 获取图片内容
     *
     * @param ids
     * @return
     */
    public List<ImgInfo> getPic(List<Integer> ids) {
        return imgService.getPic(ids);
    }

    /**
     * 获取菜单
     *
     * @return
     */
    public List<Menu> getMenu() {
        return menuDao.getMenu();
    }

    /**
     * 根据type获取配置信息
     *
     * @param type
     * @return
     */
    public List<ConfigVo> getConfig(Integer type) {
        return configDao.getConfig(type);
    }

    /**
     * 获取当前用户点赞的列表
     *
     * @param currentId 当前用户
     * @return
     */
    public List<Integer> getCircleLikeList(Integer currentId) {
        return likeContentRelationDao.getCircleLikeList(currentId);
    }


    /**
     * 获取当前用户的主题内容
     *
     * @param topic 主题id
     * @return
     */
    public CircleInfo getCurrentTopicInfo(Integer topic) {
        return circleInfoDao.getUserTopicCircleInfoById(topic);
    }

    /**
     * 获取文章信息
     *
     * @return
     */
    public List<CircleInfo> circleInfo() {
        return circleInfoDao.circleInfo();
    }

    /**
     * 保存文章内容
     *
     * @param currentId           用户id
     * @param circleContentSaveVo {@link CircleContentSaveVo}
     */
    @Transactional
    public void saveCircleContent(Integer currentId, CircleContentSaveVo circleContentSaveVo) {
        CircleContent circleContent = new CircleContent();
        String pics = circleContentSaveVo.getPics();
        circleContent.setContenttitle(circleContentSaveVo.getTitle());
        circleContent.setContent(circleContentSaveVo.getContent());
        circleContent.setImgid(circleContentSaveVo.getPics());
        circleContent.setUserid(currentId);
        circleContent.setCreateTime(new Date());
        if (pics != null) {
            List<String> imgPathFromIds = imgService.getImgPathFromIds(Arrays.asList(pics.split(",")));
            circleContent.setContentpic(String.join(",", imgPathFromIds));
        }
        circleContentDao.insert(circleContent);

    }

    @Transactional
    public void saveDiscuss(Integer currentId, DiscussSaveVo discussSaveVo) {
        Integer content_id = discussSaveVo.getContent_id();
        CircleContent has = circleContentDao.getContentById(content_id);
        if (has == null) {
            log.info("保存评论时未发现对应的文章：contentId:{}", content_id);
        } else {
            has.incrementFloor();
            has.incrementTalk();
            circleContentDao.updateByPrimaryKey(has);
            Discuss discuss = new Discuss();
            discuss.setDiscussContent(discussSaveVo.getDiscuss());
            discuss.setCreateTime(new Date());
            discuss.setContentId(content_id);
            discuss.setImgs(discussSaveVo.getPics());
            discuss.setFloor(has.getFloor());
            discuss.setUserId(currentId);
            discussDao.insert(discuss);
        }
    }

    public void createCircle(Integer currentId, CircleSaveVo circleSaveVo) {
        CircleInfo circleInfo = new CircleInfo();
        circleInfo.setAvatar(circleSaveVo.getPic());
        circleInfo.setUserId(currentId);
        circleInfo.setName("topic");
        circleInfo.setIntroduction(circleSaveVo.getName());
        circleInfoDao.insert(circleInfo);
    }

    @Transactional
    public void saveReply(Integer currentId, ReplySaveVo replySaveVo) {
        Integer discuss_id = replySaveVo.getDiscuss_id();
        Discuss has = discussDao.selectByPrimaryKey(discuss_id);
        if (has == null) {
            log.info("保存评论时未发现对应的文章：contentId:{}", discuss_id);
        } else {
            Integer userId = has.getUserId();
            String nickname = userService.getUserNickname(userId);
            String currentNickname = userService.getUserNickname(currentId);
            DiscussReply reply = new DiscussReply();
            String pics = replySaveVo.getPics();
            if (!StringUtils.isBlank(pics)) {
                List<String> imgPathFromIds = imgService.getImgPathFromIds(Arrays.asList(pics.split(",")));
                reply.setImgs(String.join(",", imgPathFromIds));
                reply.setReplyContent(replySaveVo.getReplyContent());
                reply.setUserId(currentId);
                reply.setCreateTime(new Date());
                reply.setDiscussId(discuss_id);
                reply.setToUser(userId);
                reply.setToUsername(nickname);
                discussReplyDao.insert(reply);
            }
            UserMsg userMsg= new UserMsg();
            userMsg.setUserId(userId);
            userMsg.setFromUserId(currentId);
            userMsg.setMsgType(4);
            userMsg.setFromUsername(currentNickname);
            userMsg.setDiscussId(discuss_id);
            userMsg.setCreateTime(new Date());
            userMsg.setContentId(replySaveVo.getContent_id());
            userService.saveUserMsg(userMsg);

        }
    }

    public CircleInfo getDefaultCircle() {
        return getCurrentTopicInfo(5);
    }
}
