package org.zerock.controller;

import org.springframework.security.core.Authentication; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {

	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		
		log.info("access Denied : " + auth);
		
		model.addAttribute("msg", "Access Denied");
	}
	
	
	//���� �޽����� �α׾ƿ� �޽����� �Ķ���ͷ� ����Ѵ�.
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("����: " + error);
		log.info("�α׾ƿ�: " + logout );
		
		if(error != null) {
			model.addAttribute("error", "�α��� �����Դϴ�. ������ Ȯ�����ּ���");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "logout!!");
		}
	}
	
	//�α׾ƿ� ó��
	@GetMapping("/customLogout")
	public void logoutGET() {
		
		log.info("custom logout");
	}
}
