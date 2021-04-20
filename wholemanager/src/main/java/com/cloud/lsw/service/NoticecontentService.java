package com.cloud.lsw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.lsw.common.utils.PageUtils;
import com.cloud.lsw.entity.NoticecontentEntity;
import com.cloud.lsw.entity.ScoreinfoEntity;
import com.cloud.lsw.service.impl.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:33
 */
public interface NoticecontentService {

    int insertNoticeInfo(Map<String, Object> map);

    List<NoticecontentEntity> getNoticeList(Map<String, Object> map);

    NoticecontentEntity getEntityById(Integer id);

    Integer deleteNotice(Integer id);

    Integer updateNotice(Map<String, Object> map);

    List<HashMap<String,Object>> getNoticeListWithCreater(Map<String,Object> map);
}

