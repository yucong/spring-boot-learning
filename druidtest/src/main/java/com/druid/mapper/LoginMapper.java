package com.druid.mapper;

import com.druid.entity.LoginUser;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName druidtest
 * @Auther:GuoFeng
 * @Date: 2019/6/20.
 * @Desoription TODO
 */
@Repository
public interface LoginMapper {

    LoginUser searchLoginUserById(Integer loginUserId);
}
