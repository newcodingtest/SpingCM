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

public class SampleController { //XML,JSON 방식의 데이터 생성
	
	@GetMapping(value="/getText", produces="text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: "+MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	//스프링 5.2 버전부터  MediaType.APPLICATION_JSON_UTF8_VALUE 방식이 아닌 MediaType.APPLICATION_XML_VALUE 방식이 사용됨
	//1.produces 속성은 생략이 가능
	@GetMapping(value="/getSample", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		return new SampleVO(112,"스타","로드");
	}
	
	//2. (1)은 이런식으로 생략이 가능
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		
		return new SampleVO(113,"로켓","라쿤");
	}
	
	//3.여러 데이터를 한번에 전송하기 위해 맵타입 사용
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		// 내부적으로 1 부터 10 미만까지 루프를 처리하면서 SampleVO 객체를 만들어 List<SampleVO>로 만들어냄
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"First", i+"Last")).collect(Collectors.toList());
	}
	
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map=new HashMap<String, SampleVO>();
		map.put("First", new SampleVO(111,"그루트","주니어"));
		
		return map;
	}
	
	//4.Rest 방식으로 호출하는 경우 화면 자체가 아니라 데이터 자체를 전송하는 방식으로 처리되기 때문에
	// 데이터를 요청한 쪽에서는 정상적인 데이터인지 구분하는 방법을 제공해야함.
	//ResponseEntity는 데이터와 함께 http 헤더의 상태 메시지 등을 같이 전달하는 용도로 사용
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
	
	//5.Rest 방식에서는 URL 자체에 데이터를 식별할 수 있는 정보들을 표현
	//@PathVariable 을 사용하여 URL 상에 경로의 일부를 파라미터로 사용할 수 있음.
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) {
		
		return new String[] { "category: " +cat , "productid: " +pid};
	}
	
	
	//6.@RequestBody 는 전달된 요청의 내용을 이용해 해당 파라미터의 타입변환을 요구한다. 다양한 포멧의 입력데이터를 변환할 수 있으며, 
	// 대부분 json데이터를 서버에 보내 원하는 타입의 객체로 변환하는 용도로 사용되며, 경우 따라선 원하는 포맷의 데이터를 보내고 이를 해석해서 원하는 타입으로 사용하기도 함
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert...........ticket" +ticket);
		
		return ticket;
	}
}
