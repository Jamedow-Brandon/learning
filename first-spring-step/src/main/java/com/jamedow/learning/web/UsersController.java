package com.jamedow.learning.web;

import com.jamedow.learning.entity.Users;
import com.jamedow.learning.service.UsersService;
import com.jamedow.learning.utils.Constant;
import com.jamedow.learning.utils.MD5.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yoyo on 2017/2/15.
 */
@Controller
@RequestMapping("/login")
public class UsersController {

    @Autowired
    private UsersService usersService;




    @RequestMapping(value = "/accessLogin",produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String accessLogin(String userName, String password){

        if(StringUtils.isBlank(userName))
            return UsersService.ACCOUNT_OR_PASSWORD_ERROR;

        Users users = usersService.getUserByName(userName);
        if(users == null)
            users = usersService.getUserByEmail(userName);

        if(users == null)
            users = usersService.getUserByMobile(userName);

        if(users == null)
            return UsersService.ACCOUNT_OR_PASSWORD_ERROR;

        password = MD5.md5crypt(password);

        if(!password.equals(users.getPassword()))

            return UsersService.ACCOUNT_OR_PASSWORD_ERROR;


        return UsersService.LOGIN_SUCCESS;


    }

    @RequestMapping(value ="/signup",produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String signup(String userName,String password){

        if(StringUtils.isNotBlank(userName)&&
                StringUtils.isNotBlank(password)){

            Users user =  usersService.getUserByName(userName);

            if(user!=null)
                return UsersService.USER_EXIST;//用户已存在

            user = new Users();
            user.setId((long)(Constant.ZERO));
            user.setUsername(userName);
            password = MD5.md5crypt(password);
            user.setPassword(password);

            if((Constant.ZERO)!=usersService.saveUser(user)){

                return UsersService.SIGNUP_SUCCESS;
            }
        }
        return UsersService.SIGNUP_ERROR;

    }
}
