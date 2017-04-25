package indi.learn.persistence.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	private static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory == null) {
			InputStream inputStream = null;
			
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				return (new SqlSessionFactoryBuilder()).build(inputStream);
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession() {
		return MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	}
}
