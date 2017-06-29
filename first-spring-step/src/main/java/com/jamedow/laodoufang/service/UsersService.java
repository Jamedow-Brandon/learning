package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Users;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface UsersService {

    public static final String LOGIN_ERROR="登录失败";

    public static final String LOGIN_SUCCESS="登录成功";

    public static final String NOT_ACCOUNT = "用户不存在";

    public static final String ACCOUNT_OR_PASSWORD_ERROR = "用户账号或密码错误";

    public static final String SIGNUP_SUCCESS="注册成功";

    public static final String SIGNUP_ERROR="注册失败";

    public static final String USER_EXIST="用户存在";

    /**
     * 新增或修改用户
     * @param users
     * @return
     */
    public int saveUser(Users users);

    /**
     * 删除用户
     * @param usersId 用户id
     * @return
     */
    public int deleteUser(long usersId) ;

    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    public Users getUser(long userId);



     /**
     * 根据name查找用户
     * @param userName
     * @return
     */
    public Users getUserByName(String userName);

    /**
     * 根据email查找用户
     * @param userEmail
     * @return
     */
    public Users getUserByEmail(String userEmail);

    /**
     * 根据手机号查找用户
     * @param userMobile
     * @return
     */
    public Users getUserByMobile(String userMobile);




}
