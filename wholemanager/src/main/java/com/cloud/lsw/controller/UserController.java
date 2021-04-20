package com.cloud.lsw.controller;

import java.util.Arrays;
import java.util.Map;

import com.cloud.lsw.common.utils.PageUtils;
import com.cloud.lsw.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.cloud.lsw.entity.UserEntity;
import com.cloud.lsw.service.UserService;

import javax.annotation.Resource;


/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:33
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    @Qualifier("myRedis")
    private RedisTemplate redisTemplate;



//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    public R info(@PathVariable("id") Integer id){
////		UserEntity user = userService.getById(id);
//		UserEntity user = userService.getInfoById(id);
//
//        return R.ok().put("user", user);
//    }


}
