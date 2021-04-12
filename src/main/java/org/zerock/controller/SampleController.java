package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/sample")
@Log4j

public class SampleController { //XML,JSON ����� ������ ����
	
	@GetMapping(value="/getText", produces="text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: "+MediaType.TEXT_PLAIN_VALUE);
		
		return "�ȳ��ϼ���";
	}
	
	//������ 5.2 ��������  MediaType.APPLICATION_JSON_UTF8_VALUE ����� �ƴ� MediaType.APPLICATION_XML_VALUE ����� ����
	//1.produces �Ӽ��� ������ ����
	@GetMapping(value="/getSample", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		return new SampleVO(112,"��Ÿ","�ε�");
	}
	
	//2. (1)�� �̷������� ������ ����
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		
		return new SampleVO(113,"����","����");
	}
	
	//3.���� �����͸� �ѹ��� �����ϱ� ���� ��Ÿ�� ���
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		// ���������� 1 ���� 10 �̸����� ������ ó���ϸ鼭 SampleVO ��ü�� ����� List<SampleVO>�� ����
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"First", i+"Last")).collect(Collectors.toList());
	}
	
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map=new HashMap<String, SampleVO>();
		map.put("First", new SampleVO(111,"�׷�Ʈ","�ִϾ�"));
		
		return map;
	}
	
	//4.Rest ������� ȣ���ϴ� ��� ȭ�� ��ü�� �ƴ϶� ������ ��ü�� �����ϴ� ������� ó���Ǳ� ������
	// �����͸� ��û�� �ʿ����� �������� ���������� �����ϴ� ����� �����ؾ���.
	//ResponseEntity�� �����Ϳ� �Բ� http ����� ���� �޽��� ���� ���� �����ϴ� �뵵�� ���
	@GetMapping(value="/check",params= {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		
		SampleVO vo=new SampleVO(0,""+height, ""+weight);
		
		ResponseEntity<SampleVO> result=null;
		
		if(height<150) {
			result=ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result=ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	//5.Rest ��Ŀ����� URL ��ü�� �����͸� �ĺ��� �� �ִ� �������� ǥ��
	//@PathVariable �� ����Ͽ� URL �� ����� �Ϻθ� �Ķ���ͷ� ����� �� ����.
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) {
		
		return new String[] { "category: " +cat , "productid: " +pid};
	}
	
	
	//6.@RequestBody �� ���޵� ��û�� ������ �̿��� �ش� �Ķ������ Ÿ�Ժ�ȯ�� �䱸�Ѵ�. �پ��� ������ �Էµ����͸� ��ȯ�� �� ������, 
	// ��κ� json�����͸� ������ ���� ���ϴ� Ÿ���� ��ü�� ��ȯ�ϴ� �뵵�� ���Ǹ�, ��� ���� ���ϴ� ������ �����͸� ������ �̸� �ؼ��ؼ� ���ϴ� Ÿ������ ����ϱ⵵ ��
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert...........ticket" +ticket);
		
		return ticket;
	}
}
