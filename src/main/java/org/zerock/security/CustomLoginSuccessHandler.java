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
//�α��� ������ Ư���� ������ �����ϰ� ������ ���� ���, � ��η� �α��� �������� ������ ������ admin ���������� �̵� �Ǵ� ������ ��� ���� �����ؼ� ó���ϰ� ���� ��� AuthenticationSuccessHandler ����
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler  {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

			log.warn("�α��� ����");
			
			List<String> roleNames= new ArrayList<String>();
			
			auth.getAuthorities().forEach(authority -> {
				roleNames.add(authority.getAuthority());
			});
			
			log.warn("Role names: " +roleNames);
			
			//�α��� ������������ ������ admin �̸� /sample/admin �������� �̵�
			if(roleNames.contains("ROLE_ADMIN")){
				response.sendRedirect("/sample/admin");
				return;
			}
			
			//�α��� ������������ ������ member �̸� /sample/member �������� �̵�
			if(roleNames.contains("ROLE_MEMBER")) {
				response.sendRedirect("/sample/member");
				return;
			}
			response.sendRedirect("/");
	}
	
	

}
