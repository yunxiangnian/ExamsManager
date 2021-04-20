package com.cloud.lsw.service;

import com.cloud.lsw.entity.FileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author lisw
 * @create 2021/4/18 22:07
 */
public interface FileUploadService {
    /**
     * 插入学生上传的pdf文件
     * @param fileEntity
     * @return
     */
    Integer insertIntoFileInfo(FileEntity fileEntity);

    List<FileEntity> getApplyResult(String loginUser);

    Integer booleanMultiplyApply(String noticeName, String userName);

    Integer updateRemarkAndStateByUUID(Map<String,Object> map);
}
