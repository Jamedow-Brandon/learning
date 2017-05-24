package com.jamedow.learning.web;

import com.jamedow.learning.entity.Users;
import com.jamedow.learning.service.UsersService;
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




    @RequestMapping("/accessLogin")
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

    @RequestMapping("/signup")
    @ResponseBody
    public String signup(String userName,String password){

        Users user = null;

        if(StringUtils.isNotBlank(userName)&&
                StringUtils.isNotBlank(password)){

            user =  usersService.getUserByName(userName);

            if(user==null)
                return UsersService.USER_EXIST;

            user.setUsername(userName);
            password = MD5.md5crypt(password);
            user.setPassword(password);

            if(usersService.saveUser(user)==0){

                return UsersService.SIGNUP_SUCCESS;
            }
        }
        return UsersService.SIGNUP_ERROR;

    }
}
