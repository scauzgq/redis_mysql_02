package com.scauzgq.redis_mysql_02.test;

import com.scauzgq.redis_mysql_02.beans.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;

public class MyBatisFirstCacheTest {

	public static void main(String[] args) throws Exception {
		String resource = "spring/spring-mybatis-firstCacheTest.xml";

		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder()
				.build(reader);
		SqlSession session = factory.openSession();
		String statement = "com.scauzgq.redis_mysql_02.dao.UserDao.selectUserByUserName";
		User user = session.selectOne(statement, "scauzgq1");
		System.out.println(user);
		System.out.println("--------------------------------------------------------------------");

        /*
         * MyBatis一级缓存默认开启
         */
		user = session.selectOne(statement, "scauzgq1");
		System.out.println(user);
		System.out.println("--------------------------------------------------------------------");

		/*
		 * 清空缓存，重新查询
		 */
		session.clearCache();

		user = session.selectOne(statement, "scauzgq1");
		System.out.println(user);
		System.out.println("--------------------------------------------------------------------");

		/*
		 * 执行update操作也会清空缓存
		 */
		session.update("com.scauzgq.redis_mysql_02.dao.UserDao.updateUser", new User("scauzgq1","fuck"));
		user = session.selectOne(statement, "scauzgq1");
		System.out.println(user);
		System.out.println("--------------------------------------------------------------------");


	}
}
