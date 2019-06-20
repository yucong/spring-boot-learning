package com.druid.service;

import com.druid.entity.LoginUser;
import com.druid.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName druidtest
 * @Auther:GuoFeng
 * @Date: 2019/6/20.
 * @Desoription TODO
 */
@Service
public class LoginUserService {

    @Autowired
    private LoginMapper loginMapper;

    public LoginUser getLoginUser(Integer id){
        return loginMapper.searchLoginUserById(id);
    }
}
