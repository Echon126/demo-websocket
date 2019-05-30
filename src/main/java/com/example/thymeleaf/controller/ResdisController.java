package com.example.thymeleaf.controller;

import com.example.thymeleaf.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-05-30 09:44
 **/
@RestController
public class ResdisController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/redisInfo")
    public String getRedisInfo() {
        return (String) redisUtil.get("1");
    }

    @RequestMapping("/setRedisInfo")
    public void setRedisInfo() {
        redisUtil.set("1", "sssssssssssssssssssssssssss");
    }

}
