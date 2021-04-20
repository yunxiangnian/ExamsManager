package com.cloud.lsw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import com.cloud.lsw.dao.UserDao;
import com.cloud.lsw.entity.UserEntity;
import com.cloud.lsw.service.UserService;

import javax.annotation.Resource;


/**
 * @author 11692
 */
@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {
     /**记录日志*/
    private static final Logger USERSERVICELOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDao userDao;

    @Override
    public UserEntity getInfoByPhoneNumber(String phoneNumber) {
        UserEntity userEntity = userDao.getInfoByPhone(phoneNumber);
        USERSERVICELOGGER.info("查询的phoneNumber为："+ phoneNumber +"数据为：" + userEntity);
        return userEntity;
    }

    @Override
    public Integer registerValid(String phoneNumber) {
        return userDao.registerValid(phoneNumber);
    }

    @Override
    public boolean insertEntity(UserEntity user) {
        USERSERVICELOGGER.info("开始添加用户：" + user);
        return userDao.insertEntity(user);
    }

    @Override
    public HashMap<String, Object> getUserApplyNoticeInfo(Map<String,Object> map) {
        return userDao.getUserApplyNoticeInfo(map);
    }


}
