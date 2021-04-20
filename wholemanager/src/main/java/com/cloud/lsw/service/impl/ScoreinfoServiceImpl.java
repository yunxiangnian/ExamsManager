package com.cloud.lsw.service.impl;

import com.cloud.lsw.common.utils.PageUtils;
import com.cloud.lsw.common.utils.Query;
import com.cloud.lsw.dao.ScoreInfoDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cloud.lsw.entity.ScoreinfoEntity;
import com.cloud.lsw.service.ScoreinfoService;

import javax.annotation.Resource;


/**
 * @author 11692
 */
@Service("scoreinfoService")
public class ScoreinfoServiceImpl extends BaseService implements ScoreinfoService {

    @Resource
    private ScoreInfoDao scoreInfoDao;

    @Override
    public List<String> getNoticeNameByUser(String loginUserName) {
        return scoreInfoDao.getNoticeNameByUser(loginUserName);
    }

    @Override
    public Integer insertScoreInfo(Map<String, Object> map) throws Exception {
        return scoreInfoDao.insertScoreInfo(map);
    }

    @Override
    public List<ScoreinfoEntity> getScoreInfoWithUser(Map<String,Object> map) {
        return scoreInfoDao.getScoreInfoWithUser(map);
    }

    @Override
    public ScoreinfoEntity getInfoById(Integer id) {
        return scoreInfoDao.getInfoById(id);
    }

    @Override
    public Integer updateScoreInfo(Map<String, Object> map) {
        return scoreInfoDao.updateScoreInfo(map);
    }

    @Override
    public Integer deleteScoreInfo(Integer id) {
        return scoreInfoDao.deleteScoreInfo(id);
    }

    @Override
    public List<ScoreinfoEntity> getStuScoreInfo(String loginUser) {
        return scoreInfoDao.getStuScoreInfo(loginUser);
    }
}
