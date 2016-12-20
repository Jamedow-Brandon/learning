package com.jamedow.learning.service;

import com.jamedow.learning.entity.Users;
import com.jamedow.learning.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 365 on 2016/12/12 0012.
 */
@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public int insertUser(Users users) {
        return usersMapper.insertSelective(users);
    }
}
