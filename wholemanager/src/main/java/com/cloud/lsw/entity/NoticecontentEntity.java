package com.cloud.lsw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:33
 */
@Data
@TableName("tb_noticecontent")
public class NoticecontentEntity implements Serializable {
	private static final long serialVersionUID = 1L;



	/**主键名字*/
	@TableId
	private Integer id;

	/**
	 * 公告名字
	 */
	private String name;
	/**
	 * 公告内容
	 */
	private String content;

	/**公告的状态 1 为有效 0 为无效*/
	private String notice_status;

	/**创建人的姓名*/
	private String createUser;

	/**创建时间*/

	private Date createTime;

	/**更新时间*/
	private Date modifiedTime;

}
