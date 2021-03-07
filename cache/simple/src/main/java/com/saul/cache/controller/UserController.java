package com.saul.cache.controller;

import com.saul.cache.dao.entity.User;
import com.saul.cache.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User getUsr(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
