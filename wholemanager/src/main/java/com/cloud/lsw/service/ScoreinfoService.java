package com.cloud.lsw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lsw.common.utils.PageUtils;
import com.cloud.lsw.entity.ScoreinfoEntity;
import com.cloud.lsw.service.impl.BaseService;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:32
 */
public interface ScoreinfoService {

    /**
     * 获取当前用户的所有的通告信息
     * @param loginUserName
     * @return
     */
    List<String> getNoticeNameByUser(String loginUserName);

    Integer insertScoreInfo(Map<String, Object> map) throws Exception;

    List<ScoreinfoEntity> getScoreInfoWithUser(Map<String,Object> map);

    ScoreinfoEntity getInfoById(Integer id);

    Integer updateScoreInfo(Map<String, Object> map);

    Integer deleteScoreInfo(Integer id);

    List<ScoreinfoEntity> getStuScoreInfo(String loginUser);
}

