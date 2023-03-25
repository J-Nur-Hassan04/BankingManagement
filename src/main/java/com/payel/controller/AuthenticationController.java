package com.payel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController{

	@RequestMapping("/login")
	public ModelAndView getLoginForm()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("LoginPage");
		return modelAndView;
	}
}
