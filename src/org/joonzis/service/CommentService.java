package org.joonzis.service;

import java.util.List;

import org.joonzis.vo.CVO;

public interface CommentService {
	public List<CVO> getAllComment(int b_idx);
	public int insertComment(CVO cvo);
	public int removeComment(int b_idx);
}
