package org.zerock.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j

//��й�ȣ�� �����ϰ� ������ �� �ֵ��� ��й�ȣ�� �ܹ��� ��ȣȭ�� �����ϴ� PasswordEncoder �������̽��� ����ü���� �����մϴ�
public class CustomNoOpasswordEncoder implements PasswordEncoder {

	//��й�ȣ�� �ܹ��� ��ȣȭ
	@Override
	public String encode(CharSequence rawPassword) {
		
		log.warn("Before encode: " + rawPassword);
		return rawPassword.toString();
	}

	//��ȣȭ���� ���� ��й�ȣ(raw-)�� ��ȣȭ�� ��й�ȣ(encoded-)�� ��ġ�ϴ��� ��
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		log.warn("mathes: "+ rawPassword + ":" + encodedPassword);
		return rawPassword.toString().equals(encodedPassword);
	}

}
