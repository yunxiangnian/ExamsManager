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
 * @date 2021-03-23 17:57:33
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户主键
	 */
	@TableId
	private Integer id;

	/**登录凭证*/
	private String phoneNumber;

	/**
	 * 账户姓名
	 */

	private String username;
	/**
	 * 账户密码
	 */
	private String password;

	/**
	 * 账户是否为老师
	 */
	private String isTea;

	public UserEntity(String phoneNumber,String username,String password, String isTea){
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.isTea = isTea;
	}
}
