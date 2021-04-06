package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void testPrint() {
		
		log.info(service);
	}
	
	@Test
	public void testGetList() {
		
		service.getList().forEach(board->log.info(board));
	}
	
	@Test
	public void testRegister() {
		BoardVO vo=new BoardVO();
		vo.setTitle("AAATest 테스트");
		vo.setContent("AAAContent 테스트");
		vo.setWriter("tester");
		
		long bno=service.register(vo);
		
		log.info("BNO: "+vo);
	}
	
	@Test
	public void testGet() {
		log.info(service.get(2l));
	}
	
	@Test
	public void testDel() {
		log.info(service.remove(2l));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board=service.get(3l);
		
		if(board==null) {
			return;
		}
		
		board.setTitle("제목 수정 테스트");
		log.info("제목 수정 결과: "+service.modify(board));
	}
	
}
