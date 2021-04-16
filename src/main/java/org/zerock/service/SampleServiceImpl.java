package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	//테스트 코드를 작성하면서 log.info()를 이용해 로그를 기록하는 부분을 AOP를 적용하여 중복 기록하는 부분을 제거 시킬것이다.
	
	
	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(str1)+Integer.parseInt(str2);
	}
	

}
