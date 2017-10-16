package com.erpnext.framework.util;

import java.security.SecureRandom;
import java.util.UUID;

public class IDUtils {
	
	private static SecureRandom random = new SecureRandom();
	
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	

	public static void main(String[] args) {
		System.out.println(IDUtils.randomLong());
		System.out.println(IDUtils.uuid());
	}

}
