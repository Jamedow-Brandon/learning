package com.jamedow.learning.mapper;

import com.jamedow.learning.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * Created by 365 on 2016/12/12 0012.
 */
@Repository
public interface UsersMapper {

    /**
     * 添加新用户
     *
     * @param users
     * @return
     */
    int insertSelective(Users users);
}
