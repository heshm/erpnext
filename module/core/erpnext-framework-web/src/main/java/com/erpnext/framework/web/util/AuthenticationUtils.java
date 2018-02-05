package com.erpnext.framework.web.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class AuthenticationUtils {
	
	public static UserDetails getPrincipal(){
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext != null) {
			Authentication auth = securityContext.getAuthentication();
			if(auth != null){
				Object principal = auth.getPrincipal();
	            if (principal instanceof UserDetails) {
	            	return (UserDetails) principal;
	            }
			}
		}
		return null;
	}
	
	public static String getUserId() {
		return getPrincipal().getUsername();
	}
	
	public static boolean isThePrincipal(String userId){
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if(securityContext != null && !StringUtils.isEmpty(userId)){
			Authentication auth = securityContext.getAuthentication();
			if(auth != null){
				Object principal = auth.getPrincipal();
				if (principal instanceof UserDetails && userId.equals(((UserDetails) principal).getUsername())) {
	            	return true;
	            }
			}
		}
		return false;
	}

}
