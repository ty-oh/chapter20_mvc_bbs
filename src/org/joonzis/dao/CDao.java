package org.joonzis.dao;

import java.util.List;

import org.joonzis.vo.CVO;

public interface CDao {
	List<CVO> selectAllComment(int b_idx);
}
