package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Users;
import com.jamedow.laodoufang.entity.UsersExample;
import com.jamedow.laodoufang.mapper.UsersMapper;
import com.jamedow.laodoufang.service.UsersService;
import com.jamedow.laodoufang.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
public class UsersServiceImpl implements UsersService{


    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int saveUser(Users users) {

        if(Constant.ZERO!=users.getId()) {
            return usersMapper.updateByPrimaryKeySelective(users);
        }
        users.setDelStatus(Constant.Y);
        return usersMapper.insert(users);

    }

    @Override
    public int deleteUser(long usersId) {

        if(Constant.ZERO!=usersId) {
            return usersMapper.deleteByPrimaryKey(usersId);
        }
        return Constant.ZERO;
    }

    @Override
    public Users getUser(long usersId) {

        if(Constant.ZERO!=usersId) {
            return usersMapper.selectByPrimaryKey(usersId);
        }
        return null;
    }




    @Override
    public Users getUserByName(String userName) {

        if (StringUtils.isNotBlank(userName)) {

            userName = userName.trim();
            UsersExample example = new UsersExample();
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andDelStatusEqualTo(Constant.Y).andUsernameEqualTo(userName);
            List<Users> usersList = usersMapper.selectByExample(example);
            if (usersList != null && !usersList.isEmpty()) {
                return usersList.get(0);
            }
        }

        return null;
    }

    @Override
    public Users getUserByEmail(String userEmail) {

        if (StringUtils.isNotBlank(userEmail)) {

            userEmail = userEmail.trim();
            UsersExample example = new UsersExample();
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andDelStatusEqualTo(Constant.Y).andEmailEqualTo(userEmail);
            List<Users> usersList = usersMapper.selectByExample(example);
            if (usersList != null && !usersList.isEmpty()) {

                return usersList.get(0);
            }
        }

        return null;
    }

    @Override
    public Users getUserByMobile(String userMobile) {
        if (StringUtils.isNotBlank(userMobile)) {

            userMobile = userMobile.trim();
            UsersExample example = new UsersExample();
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andDelStatusEqualTo(Constant.Y).andMobileEqualTo(userMobile);
            List<Users> usersList = usersMapper.selectByExample(example);
            if (usersList != null && !usersList.isEmpty()) {

                return usersList.get(0);
            }
        }

        return null;
    }

}
