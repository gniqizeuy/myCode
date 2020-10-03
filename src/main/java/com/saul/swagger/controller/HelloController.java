package com.saul.swagger.controller;

import com.saul.swagger.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yzq")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @ApiOperation("你爹的接口")
    @PostMapping("/getUser")
    public User getUser(){
        return new User(); // 实体在请求接口的返回值上，即使是泛型，都能映射到实体项目中
    }


}
