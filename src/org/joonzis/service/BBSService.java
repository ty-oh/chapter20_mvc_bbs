package org.joonzis.service;

import java.util.List;
import java.util.Map;

import org.joonzis.vo.BVO;

public interface BBSService {
	public int recordCount();
	public List<BVO> getList(Map<String, Integer> map);
}
