package org.zerock.security;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;




@Log4j
//로그인 성공후 특정한 동작을 제어하고 싶으면 예를 들어, 어떤 경로로 로그인 페이지로 들어오면 무조건 admin 페이지로의 이동 또는 별도의 쿠기 등을 생성해서 처리하고 싶은 경우 AuthenticationSuccessHandler 구현
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler  {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

			log.warn("로그인 성공");
			
			List<String> roleNames= new ArrayList<String>();
			
			auth.getAuthorities().forEach(authority -> {
				roleNames.add(authority.getAuthority());
			});
			
			log.warn("Role names: " +roleNames);
			
			//로그인 성공했을때의 권한이 admin 이면 /sample/admin 페이지로 이동
			if(roleNames.contains("ROLE_ADMIN")){
				response.sendRedirect("/sample/admin");
				return;
			}
			
			//로그인 성공했을때의 권한이 member 이면 /sample/member 페이지로 이동
			if(roleNames.contains("ROLE_MEMBER")) {
				response.sendRedirect("/sample/member");
				return;
			}
			response.sendRedirect("/");
	}
	
	

}
