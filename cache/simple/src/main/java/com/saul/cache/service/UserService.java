package com.saul.cache.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.saul.cache.dao.entity.User;


public interface UserService extends IService<User> {

    User getUserById(Integer id);

}
