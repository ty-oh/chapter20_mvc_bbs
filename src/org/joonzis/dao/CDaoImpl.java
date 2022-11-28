package org.joonzis.dao;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;

public class CDaoImpl implements CDao{
	private static CDaoImpl instance = null;
	private CDaoImpl() {}
	public static CDaoImpl getInstance() {
		if(instance == null) {
			instance = new CDaoImpl();
		}
		return instance;
	}
	
	//필드
	private static SqlSession sqlsession = null;
	//싱글톤
	private synchronized static SqlSession getSqlSession() {
		if (sqlsession == null) {
			//factory를 선언하지 않고 바로 sqlseesion을 만든다.
			sqlsession = DBService.getFactory().openSession(false);
			// open Session(false) 수동 커밋 -> 기본 값을 수동 커밋 상태로 세션을 가져옴
		}
		return sqlsession;
	}
	
	
}
