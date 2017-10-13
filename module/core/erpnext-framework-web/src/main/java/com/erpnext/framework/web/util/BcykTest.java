package com.erpnext.framework.web.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcykTest {

	public static void main(String[] args) {
		String password = "admin";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(password));

	}

}
