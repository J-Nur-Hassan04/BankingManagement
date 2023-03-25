package com.payel.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginSuccessHandeler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String redirectUrl = request.getContextPath();
		if(userPrincipal.getRole().equals("Admin"))
		{
			redirectUrl = "/admin/home";
		}else if(userPrincipal.getRole().endsWith("User"))
		{
			redirectUrl = "/user/home";
		}
		response.sendRedirect(redirectUrl);
	}

	
}
