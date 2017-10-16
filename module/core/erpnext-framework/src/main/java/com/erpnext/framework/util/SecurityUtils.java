package com.erpnext.framework.util;

import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

	public static String encodeBase64(String s) {
		byte[] encodeByte = Base64.getEncoder().encode(s.getBytes());
		return new String(encodeByte);
	}
	
	public static String decodeBase64(String s){
		byte[] decodeByte = Base64.getDecoder().decode(s);
		return new String(decodeByte);
	}
	
	public static String encodeBCrypt(String s){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(s);
	}

	public static void main(String[] args) {
		String s = "my-trusted-client-with-secret:somesecret";
		System.out.println(SecurityUtils.encodeBase64(s));

		System.out.println(SecurityUtils.decodeBase64(encodeBase64(s)));
		
		System.out.println(decodeBase64("bXktdHJ1c3RlZC1jbGllbnQtd2l0aC1zZWNyZXQ6c29tZXNlY3JldA=="));
		
		System.out.println(encodeBCrypt("guest"));
	}

}
