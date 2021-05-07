package org.zerock.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j

//비밀번호를 안전하게 저장할 수 있도록 비밀번호의 단방향 암호화를 지원하는 PasswordEncoder 인터페이스와 구현체들을 제공합니다
public class CustomNoOpasswordEncoder implements PasswordEncoder {

	//비밀번호를 단방향 암호화
	@Override
	public String encode(CharSequence rawPassword) {
		
		log.warn("Before encode: " + rawPassword);
		return rawPassword.toString();
	}

	//암호화되지 않은 비밀번호(raw-)와 암호화된 비밀번호(encoded-)가 일치하는지 비교
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		log.warn("mathes: "+ rawPassword + ":" + encodedPassword);
		return rawPassword.toString().equals(encodedPassword);
	}

}
