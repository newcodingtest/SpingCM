package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.service.BoardServiceTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	

	private MockMvc mockMvc; //��¥�� mvc ���� �׽�Ʈ�� �Ҽ� �ִ°�,url�� �Ķ���� ���� ���������� ����ϴ� ��ó�� ����
	
	@Before   //�׽�Ʈ ���� ����Ǵ� �޼���
	public void setup() {
		this.mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
			.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testRegister() throws Exception {
		
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "Test �׽�Ʈ")
					.param("content", "Content �׽�Ʈ")
					.param("writer", "user10")
					)
			.andReturn().getModelAndView().getModelMap());
	}
	
	//Ư�� �Խù��� ��ȸ�� �ݵ�� bno �Ķ���Ͱ� �ʿ��ϹǷ� param�� ���ؼ� �߰��ϰ� ����
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "3"))
				.andReturn()
				.getModelAndView().getModelMap());
				
	}
	
	@Test
	public void testModify() throws Exception {
		
		String resultPage=mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno","4")
				.param("title", "444�� �����׽�Ʈ")
				.param("content", "444�� �����׽�Ʈ")
				.param("writer", "444�� �����׽�Ʈ"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	
	
}
