package org.joonzis.dao;

import java.util.List;

import org.joonzis.vo.CVO;

public interface CDao {
	public List<CVO> selectAllComment(int b_idx);
	public int insertComment(CVO cvo); 
	public int removeComment(int b_idx);
}
