package org.joonzis.mybatis.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * factory를 만드는게 목적
 * 
 * SqlSessionFactoryBuilder에서 SqlSessionFactory을 생성하고, Factory에서 SqlSession을 생성한다.
 * mybatis를 이용하려면 SqlSession이 필요하다.!!
 * 
 */
public class DBService {
	
	//필드 
	private static SqlSessionFactory factory = null;
	
	//싱글톤
	static {
		try {
			String resource = "org/joonzis/mybatis/config/sqlmap.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
//