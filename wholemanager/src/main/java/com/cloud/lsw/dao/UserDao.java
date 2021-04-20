package com.cloud.lsw.dao;

import com.cloud.lsw.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:33
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

	UserEntity getInfoByPhone(@Param("phone") String phoneNumber);

	/**判断是否存在已久注册过的用户信息*/
	Integer registerValid(@Param("phone") String phone);

	/**保存用户信息
	 * @return*/
	boolean insertEntity(@Param("entity") UserEntity user);

	/**
	 * 获取当前用户的申请竞赛的信息和申请状态
	 * @param map
	 * @return
	 */
    HashMap<String, Object> getUserApplyNoticeInfo(@Param("map") Map<String,Object> map);
}
