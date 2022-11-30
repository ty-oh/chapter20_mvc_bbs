package org.joonzis.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.MVO;

public class MDaoImpl implements MDao {
	private static MDaoImpl instance = null;
	private MDaoImpl() {}
	public static MDaoImpl getInstance() {
		if (instance == null) {
			instance = new MDaoImpl();
		}
		return instance;
	}
	
	private static SqlSession sqlSession = null;
	private synchronized SqlSession getSqlSession() {
		if (sqlSession == null) {
			sqlSession = DBService.getFactory().openSession(false);
		}
		return sqlSession;
	}
	
	@Override
	public int idCheck(String m_id) {
		return getSqlSession().selectOne("id_check", m_id);
	}
	
	@Override
	public int insertMember(MVO mvo) {
		int result = getSqlSession().insert("insert_member", mvo);
		if (result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	@Override
	public MVO login(Map<String, String> loginMap) {
		return getSqlSession().selectOne("login", loginMap);
	}
}
