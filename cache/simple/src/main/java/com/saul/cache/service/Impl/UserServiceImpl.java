package com.saul.cache.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saul.cache.dao.entity.User;
import com.saul.cache.dao.mapper.UserMapper;
import com.saul.cache.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Cacheable(key = "#id")
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
}
