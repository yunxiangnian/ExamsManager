package com.cloud.lsw.service.impl;

import com.cloud.lsw.common.utils.PageUtils;
import com.cloud.lsw.common.utils.Query;
import com.cloud.lsw.dao.NoticeContentDao;
import com.cloud.lsw.entity.ScoreinfoEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cloud.lsw.entity.NoticecontentEntity;
import com.cloud.lsw.service.NoticecontentService;

import javax.annotation.Resource;


/**
 * @author 11692
 */
@Service("noticeService")
public class NoticeServiceImpl extends BaseService implements NoticecontentService {

    @Resource
    private NoticeContentDao noticeContentDao;

    @Override
    public int insertNoticeInfo(Map<String, Object> map) {
        return noticeContentDao.insertNotice(map);
    }

    @Override
    public List<NoticecontentEntity> getNoticeList(Map<String, Object> map) {
        return noticeContentDao.getNoticeList(map);
    }

    @Override
    public NoticecontentEntity getEntityById(Integer id) {
        return noticeContentDao.getUpdateInfo(id);
    }

    @Override
    public Integer deleteNotice(Integer id) {
        return noticeContentDao.deleteNotice(id);
    }

    @Override
    public Integer updateNotice(Map<String, Object> map) {
        return noticeContentDao.updateNotice(map);
    }

    @Override
    public List<HashMap<String,Object>> getNoticeListWithCreater(Map<String,Object> map) {
        return noticeContentDao.getNoticeListWithCreater(map);
    }
}
