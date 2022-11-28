package org.joonzis.dao;

import java.util.List;
import java.util.Map;

import org.joonzis.vo.BVO;

public interface BDao {
	public int getTotalRecordCount();
	public List<BVO> getListBVO(Map<String, Integer> map);
}
