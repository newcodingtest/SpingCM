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
	
	
	//에러 메시지와 로그아웃 메시지를 파라미터로 사용한다.
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("에러: " + error);
		log.info("로그아웃: " + logout );
		
		if(error != null) {
			model.addAttribute("error", "로그인 에러입니다. 계정을 확인해주세요");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "logout!!");
		}
	}
	
	//로그아웃 처리
	@GetMapping("/customLogout")
	public void logoutGET() {
		
		log.info("custom logout");
	}
}
