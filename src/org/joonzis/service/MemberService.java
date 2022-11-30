package org.joonzis.service;

import org.joonzis.vo.MVO;

public interface MemberService {
	public int idCheck(String id);
	public int joinMember(MVO mvo);
}
