package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	//�׽�Ʈ �ڵ带 �ۼ��ϸ鼭 log.info()�� �̿��� �α׸� ����ϴ� �κ��� AOP�� �����Ͽ� �ߺ� ����ϴ� �κ��� ���� ��ų���̴�.
	
	
	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(str1)+Integer.parseInt(str2);
	}
	

}
