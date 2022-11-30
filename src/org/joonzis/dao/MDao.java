package org.joonzis.dao;

import java.util.Map;

import org.joonzis.vo.MVO;

public interface MDao {
	public int idCheck(String id);
	public int insertMember(MVO mvo);
	public MVO login(Map<String, String> mvo);
}
