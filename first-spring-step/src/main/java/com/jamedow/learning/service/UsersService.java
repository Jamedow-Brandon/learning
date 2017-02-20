package com.jamedow.learning.service;

import com.jamedow.learning.entity.Users;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface UsersService {

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
     *
     * @param contacts
     * @return
     */
    public List<Users> queryContacts(Users contacts);


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
