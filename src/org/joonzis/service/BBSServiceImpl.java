package org.joonzis.service;

import java.util.List;
import java.util.Map;

import org.joonzis.dao.BDao;
import org.joonzis.dao.BDaoImpl;
import org.joonzis.vo.BVO;

public class BBSServiceImpl implements BBSService{
	BDao bdao = BDaoImpl.getInstance();
	
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
}
