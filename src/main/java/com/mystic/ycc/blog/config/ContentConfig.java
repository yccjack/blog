package com.mystic.ycc.blog.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContentConfig {

    public static final List<Integer> ignore_content;
    static {
        ignore_content = new ArrayList<>();
        ignore_content.add(1);
        ignore_content.add(2);
        ignore_content.add(3);
    }

}
