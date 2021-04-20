package com.cloud.lsw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:32
 */
@Data
@TableName("tb_scoreinfo")
public class ScoreinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 竞赛id
	 */
	@TableId
	private Integer id;
	/**
	 * 比赛名称
	 */
	private String gameName;
	/**
	 * 学生名称
	 */
	private String studentName;
	/**
	 * 比赛成绩
	 */
	private String score;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**备注*/
	private String remark;

}
