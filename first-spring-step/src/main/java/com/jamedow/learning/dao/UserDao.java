package com.jamedow.learning.dao;

import com.jamedow.learning.dmo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by 365 on 2016/12/12 0012.
 */
@Repository
public interface UserDao {

    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);

}
