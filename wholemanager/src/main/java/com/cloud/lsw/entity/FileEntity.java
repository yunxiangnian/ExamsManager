package com.cloud.lsw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lisw
 * @create 2021/4/16 15:29
 */
@Data
@TableName("tb_uploaduserfiles")
public class FileEntity {

    /**
     * 主键id
     */
    @TableId
    private String  id;

    /**文件的属于者*/
    private String userName;

    /**竞赛名称*/
    private String noticeName;

    /**文件的url*/
    private String fileUrl;

    /**文件还是否有效*/
    private String state;

    /**用户上传时的文件名称*/
    private String oldName;

    /**文件的名称*/
    private String fileName;

    /**文件的类型*/
    private String fileType;

    /**文件的大小*/
    private String fileSize;

    /**审批意见*/
    private String remark;

}
