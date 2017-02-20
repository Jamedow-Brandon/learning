package com.jamedow.learning.web;

import com.jamedow.learning.common.MD5.MD5;
import com.jamedow.learning.entity.Users;
import com.jamedow.learning.service.UsersService;
import com.jamedow.learning.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yoyo on 2017/2/15.
 */
@Controller
@RequestMapping("/login")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @RequestMapping("/forwardLogin")
    public String forwardLogin(){
        return "login";
    }

    @RequestMapping("/accessLogin")
    public String accessLogin(HttpServletRequest request, String userAcc, String userPwd, String verifyCode){

        if(StringUtils.isBlank(userAcc))
            return Constant.LOGINERROR;

        Users users = usersService.getUserByName(userAcc);
        if(users == null)
            users = usersService.getUserByEmail(userAcc);

        if(users == null)
            users = usersService.getUserByMobile(userAcc);

        if(users == null)
            return Constant.NOT_ACCOUNT;

        userPwd = MD5.md5crypt(userPwd);

        if(!userPwd.equals(users.getPassword()))

            return Constant.ACCOUNT_OR_PASSWORD_ERROR;

        return Constant.LOGINSUCCESS;


    }
}
