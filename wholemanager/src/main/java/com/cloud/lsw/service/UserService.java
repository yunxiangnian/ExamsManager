package com.cloud.lsw.service;

import com.cloud.lsw.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:33
 */
public interface UserService {

    /**
     * 通过id获取信息
     * @param phoneNumber
     * @return
     */
    UserEntity getInfoByPhoneNumber(String phoneNumber);

    /**验证注册信息是否合法*/
    Integer registerValid(String phoneNumber);


    /**保存对象*/
    boolean insertEntity(UserEntity user);

    /**
     * 获取当前用户的申请竞赛的信息和申请状态
     * @param username
     * @return
     */
    HashMap<String, Object> getUserApplyNoticeInfo(Map<String,Object> map);
}

