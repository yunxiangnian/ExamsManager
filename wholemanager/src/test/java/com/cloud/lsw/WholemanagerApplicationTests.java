package com.cloud.lsw;

import com.cloud.lsw.dao.UserDao;
import com.mysql.cj.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class WholemanagerApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
//		System.out.println(dataSource.getClass());
//		System.out.println(TimeZone.getDefault());


	}



}
