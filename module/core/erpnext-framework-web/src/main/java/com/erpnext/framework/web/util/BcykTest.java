package com.erpnext.framework.web.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class BcykTest {

	public static void main(String[] args) {
		String password = "admin";
		BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
		Pbkdf2PasswordEncoder encoder2 = new Pbkdf2PasswordEncoder();
		for(int i = 0; i< 10; i++) {
			System.out.println(encoder1.encode(password));
			String result = encoder2.encode(password);
			System.out.println(result);
		}
	}

}

