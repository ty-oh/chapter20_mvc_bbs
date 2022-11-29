package org.joonzis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.joonzis.mybatis.config.DBService;
import org.joonzis.vo.BVO;

public class BDaoImpl implements BDao{
	private static BDaoImpl instance = null;
	private BDaoImpl() {}
	public static BDaoImpl getInstance() {
		if(instance == null) {
			instance = new BDaoImpl();
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
	
	@Override
	public int getTotalRecordCount() {
		return getSqlSession().selectOne("total_record");
	}
	
	@Override
	public List<BVO> getListBVO(Map<String, Integer> map) {
		return getSqlSession().selectList("list_bbs", map);
	}
	
	@Override
	public BVO getBVO(int b_idx) {
		return getSqlSession().selectOne("one_bbs", b_idx);
	}
	
	@Override
	public int insertBbs(BVO bvo) {
		int result = getSqlSession().insert("insert_bbs", bvo);
		if (result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	@Override
	public int updateBbs(BVO bvo) {
		int result = getSqlSession().update("update_bbs", bvo);
		if (result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
}
