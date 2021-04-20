package com.cloud.lsw.dao;

import com.cloud.lsw.entity.NoticecontentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.lsw.entity.ScoreinfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface NoticeContentDao extends BaseMapper<NoticecontentEntity> {

      /**
       * 插入新的公告
       * @param map
       * @return
       */
      int insertNotice(@Param("map")Map<String,Object> map);

      /**
       * 获取竞赛列表
       * @param map
       * @return
       */
      List<NoticecontentEntity> getNoticeList(@Param("map") Map<String, Object> map);

      /**
       * 获取编辑的通告信息
       * @param id
       * @return
       */
      NoticecontentEntity getUpdateInfo(@Param("id") Integer id);

      /**
       * 删除一个通告信息
       * @param id
       * @return
       */
      Integer deleteNotice(@Param("id") Integer id);

      Integer updateNotice(@Param("map") Map<String, Object> map);

      List<HashMap<String,Object>> getNoticeListWithCreater(@Param("map") Map<String,Object> map);
}
