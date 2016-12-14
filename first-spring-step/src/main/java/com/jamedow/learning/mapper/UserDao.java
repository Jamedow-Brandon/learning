package com.jamedow.learning.mapper;

import com.jamedow.learning.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by 365 on 2016/12/12 0012.
 */
@Repository
public interface UserDao {

    /**
     * 添加新用户
     *
     * @param userEntity
     * @return
     */
    int insertUser(UserEntity userEntity);
}
