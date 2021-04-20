package com.cloud.lsw.service.impl;

import com.cloud.lsw.dao.FileUploadDao;
import com.cloud.lsw.entity.FileEntity;
import com.cloud.lsw.service.FileUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lisw
 * @create 2021/4/18 22:08
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private FileUploadDao fileUploadDao;

    @Override
    public Integer insertIntoFileInfo(FileEntity fileEntity) {
        return fileUploadDao.insertUploadFileInfo(fileEntity);
    }

    @Override
    public List<FileEntity> getApplyResult(String loginUser) {
        return fileUploadDao.getApplyResult(loginUser);
    }

    @Override
    public Integer booleanMultiplyApply(String noticeName, String userName) {
        return fileUploadDao.booleanMultiplyApply(noticeName,userName);
    }

    @Override
    public Integer updateRemarkAndStateByUUID(Map<String,Object> map) {
        return fileUploadDao.updateRemarkAndStateByUUID(map);
    }
}
