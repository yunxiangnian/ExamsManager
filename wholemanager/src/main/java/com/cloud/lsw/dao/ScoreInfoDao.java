package com.cloud.lsw.dao;

import com.cloud.lsw.entity.ScoreinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:32
 */
@Mapper
public interface ScoreInfoDao extends BaseMapper<ScoreinfoEntity> {

    /**
     * 初始化通告信息（通过登录人的账户）
     * @param loginUserName
     * @return
     */
    List<String> getNoticeNameByUser(@Param("createUser") String loginUserName);

    /**
     * 插入成绩信息
     * @param map
     * @return
     * @throws Exception
     */
    Integer insertScoreInfo(@Param("map") Map<String, Object> map) throws Exception;

    /**
     * 查看成绩信息
     * @param map
     * @return
     */
    List<ScoreinfoEntity> getScoreInfoWithUser(@Param("map") Map<String,Object> map);

    /**
     * 获取更新模态框需要的信息
     * @param id
     * @return
     */
    ScoreinfoEntity getInfoById(@Param("id") Integer id);

    /**
     * 更新
     * @param map
     * @return
     */
    Integer updateScoreInfo(@Param("map") Map<String, Object> map);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteScoreInfo(@Param("id") Integer id);

    List<ScoreinfoEntity> getStuScoreInfo(@Param("user") String loginUser);
}
