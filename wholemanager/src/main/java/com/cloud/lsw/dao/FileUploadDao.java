package com.cloud.lsw.dao;

import com.cloud.lsw.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lisw
 * @create 2021/4/18 22:00
 */
@Mapper
public interface FileUploadDao {
    /**
     * 插入学生上传的数据
     * @param fileEntity
     * @return
     */
    Integer insertUploadFileInfo(@Param("file") FileEntity fileEntity);

    List<FileEntity> getApplyResult(@Param("user") String loginUser);

    Integer booleanMultiplyApply(@Param("noticeName") String noticeName, @Param("user") String userName);

    Integer updateRemarkAndStateByUUID(@Param("map")Map<String,Object> map);
}
