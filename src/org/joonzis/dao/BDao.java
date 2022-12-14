package org.joonzis.dao;

import java.util.List;
import java.util.Map;

import org.joonzis.vo.BVO;

public interface BDao {
	public int getTotalRecordCount();
	public List<BVO> getListBVO(Map<String, Integer> map);
	public BVO getBVO(int b_idx);
	public int insertBbs(BVO bvo);
	public int updateBbs(BVO bvo);
	public int removeBbs(int b_idx);
	public int updateHit(int b_idx);
}
