package com.jamedow.learning.service.impl;

import com.jamedow.learning.entity.UserEntity;
import com.jamedow.learning.mapper.UserDao;
import com.jamedow.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 365 on 2016/12/12 0012.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public int insertUser(UserEntity userEntity) {
        return userDao.insertUser(userEntity);
    }
}