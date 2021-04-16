package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect
public class LogAdvice {

	//execution...문자열은 AspectJ의 표현식이다. 접근제한자와 특정 클래스의 메서드를 지정할 수 있음.
	@Before( "execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		
		log.info("============================");
	}
	
	//execution으로 시작하는 포인트컷 설정에 doAdd()메서드 명시하고 파라미터값 지정함
	// 뒤족 &&args 부분은 변수명을 지정 이 두 정보를 이용하여 logBeforeWithParam 메서드의 파라미터값을 설정
	@Before( "execution(* org.zerock.service.SampleService*.doAdd(String,String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		
		log.info("str1: "+str1);
		log.info("str2: "+str2);
	}
	
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleSerivce*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		
		log.info("Exception.....!!!!!");
		log.info("exception: "+ exception);
	}
	
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime( ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target: "+pjp.getTarget());
		log.info("Param: "+Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long end= System.currentTimeMillis();
		
		log.info("TIME: "+(end - start));
		
		return result;
	}
	
}
