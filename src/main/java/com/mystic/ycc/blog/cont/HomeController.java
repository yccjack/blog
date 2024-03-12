package com.mystic.ycc.blog.cont;

import com.google.gson.Gson;
import com.mystic.ycc.blog.bean.ConfigVo;
import com.mystic.ycc.blog.entity.Menu;
import com.mystic.ycc.blog.service.BlogHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("blog")
public class HomeController {

    Gson gson = new Gson();

    @Autowired
    private BlogHomeService blogHomeService;


    @GetMapping("/getMenu")
    public String getMenu() {
        List<Menu> menu = blogHomeService.getMenu();
        return gson.toJson(menu);
    }

    @GetMapping("/config")
    public String getConfig(Integer type) {
        List<ConfigVo> configs = blogHomeService.getConfig(type);
        return gson.toJson(configs);
    }
}
