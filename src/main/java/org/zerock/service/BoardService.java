package org.zerock.service;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	
	Long register(BoardVO board);
	
	BoardVO get(Long bno);
	
	int modify(BoardVO board);
	
	int remove(Long bno);
	
	List<BoardVO> getList();
	
	List<BoardVO> getList(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	
	

	
 }
