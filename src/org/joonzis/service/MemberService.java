package org.joonzis.service;

import java.util.Map;

import org.joonzis.vo.MVO;

public interface MemberService {
	public int idCheck(String id);
	public int joinMember(MVO mvo);
	public MVO login(Map<String, String> loginMap);
	
}
