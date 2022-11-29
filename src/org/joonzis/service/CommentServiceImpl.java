package org.joonzis.service;

import java.util.List;

import org.joonzis.dao.CDao;
import org.joonzis.dao.CDaoImpl;
import org.joonzis.vo.CVO;

public class CommentServiceImpl implements CommentService{
	CDao cdao = CDaoImpl.getInstance();
	
	@Override
	public List<CVO> getAllComment(int b_idx) {
		return cdao.selectAllComment(b_idx);
	}
	
	@Override
	public int insertComment(CVO cvo) {
		return cdao.insertComment(cvo);
	}
	
	@Override
	public int removeComment(int b_idx) {
		return cdao.removeComment(b_idx);
	}
}
