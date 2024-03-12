package com.mystic.ycc.blog.service;

import com.mystic.ycc.blog.bean.LoginUser;
import com.mystic.ycc.blog.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserService userService;

    public UserInfo login(LoginUser loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        return userService.getUserInfoByNameAndPwd(username, password);

    }

    public UserInfo registry(LoginUser loginUser) {
        String username = loginUser.getUsername();
        int has = userService.hasSameNameUser(username);
        if (has > 0) {
            log.info("注册账户发现了同名账户存在，拒绝此次注册");
            throw new RuntimeException("名字重复");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(loginUser.getEmail());
        userInfo.setName(username);
        userInfo.setPassword(loginUser.getPassword());
        userInfo.setCreateTime(new Date());
        userService.saveUserInfo(userInfo);
        return userInfo;
    }
}
