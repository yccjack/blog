package com.mystic.ycc.blog.service;

import com.mystic.ycc.blog.dao.ImgInfoDao;
import com.mystic.ycc.blog.entity.ImgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService {
    @Autowired
    private ImgInfoDao imgInfoDao;
    public List<String> getImgPathFromIds(List<String> imgIds) {
        return imgInfoDao.getImgPathFromIds(imgIds);
    }

    public List<ImgInfo> getPic(List<Integer> ids) {
        return imgInfoDao.getPic(ids);
    }
}
