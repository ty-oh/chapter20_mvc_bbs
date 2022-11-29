package org.joonzis.service;

import java.util.List;
import java.util.Map;

import org.joonzis.dao.BDao;
import org.joonzis.dao.BDaoImpl;
import org.joonzis.dao.CDao;
import org.joonzis.dao.CDaoImpl;
import org.joonzis.vo.BVO;

public class BBSServiceImpl implements BBSService{
	BDao bdao = BDaoImpl.getInstance();
	CDao cdao = CDaoImpl.getInstance();
	
	@Override
	public int recordCount() {
		return bdao.getTotalRecordCount();
	}
	
	@Override
	public List<BVO> getList(Map<String, Integer> map) {
		return bdao.getListBVO(map);
	}
	
	@Override
	public BVO getBbs(int b_idx) {
		return bdao.getBVO(b_idx);
	}
	
	@Override
	public int insertBbs(BVO bvo) {
		return bdao.insertBbs(bvo);
	}
	
	@Override
	public int updateBbs(BVO bvo) {
		return bdao.updateBbs(bvo);
	}
	
	@Override
	public int removeBbs(int b_idx) {
		int cntComment = cdao.countComment(b_idx);
		if (cntComment > 0) {
			cdao.removeAllComment(b_idx);
		}
		
		return bdao.removeBbs(b_idx);
	}
	
	@Override
	public int updateHit(int b_idx) {
		return bdao.updateHit(b_idx);
	}
}
