package org.joonzis.service;

import org.joonzis.dao.MDao;
import org.joonzis.dao.MDaoImpl;
import org.joonzis.vo.MVO;

public class MemberServiceImpl implements MemberService {
	MDao mdao = MDaoImpl.getInstance();
	
	@Override
	public int idCheck(String id) {
		return mdao.idCheck(id);
	}
	
	@Override
	public int joinMember(MVO mvo) {
		return mdao.insertMember(mvo);
	}
}
