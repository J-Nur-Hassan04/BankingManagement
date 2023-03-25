package com.payel.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.payel.security.UserPrincipal;
import com.payel.service.CustomerService;
import com.payel.service.TransactionService;
import com.payel.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	UserService userService;

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/sing-up")
	public ModelAndView createCustome(@RequestParam("name") String name, @RequestParam("gender") String gender,
			@RequestParam("email") String email, @RequestParam("contact") String contact,
			@RequestParam("adhar") String adharNumber) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("userData",
				customerService.saveNewCustomer(name, gender, email, contact, adharNumber, "Savings"));
		modelandview.addObject("homeLink", "/user/home");
		modelandview.setViewName("UserCreated");
		return modelandview;
	}

	@GetMapping("/home")
	public ModelAndView getUserProfile(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		modelAndView.addObject("data", userService.getUserByEmailId(userPrincipal.getUsername()));
		modelAndView.addObject("transections", transactionService.getTransections(userPrincipal.getUsername()));
		modelAndView.setViewName("UserHomePage");
		return modelAndView;
	}

	@GetMapping("/edit-profile")
	public ModelAndView editCustomeDetails(@RequestParam("accNo") String accNo)
			throws AccountNotFoundException, NumberFormatException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("data", customerService.getCustomerDetails(Integer.parseInt(accNo)));
		modelAndView.addObject("saveLink", "/user/edit-customer");
		modelAndView.addObject("homeLink", "/user/home");
		modelAndView.setViewName("CustomeEditForm");
		return modelAndView;
	}

	@PostMapping("/edit-customer")
	public ModelAndView editCustome(@RequestParam("accNo") String accNo, @RequestParam("name") String name,
			@RequestParam("gender") String gender, @RequestParam("email") String email,
			@RequestParam("contact") String contact, @RequestParam("adhar") String adharNumber) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("userData",
				customerService.saveModifiedCustomer(accNo, name, gender, email, contact, adharNumber));
		modelandview.addObject("homeLink", "/user/home");
		modelandview.setViewName("UserCreated");
		return modelandview;
	}

	@GetMapping("/transfer-money")
	public ModelAndView getTransferMoneyForm()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("MoneyTransferForm");
		return modelAndView;
	}
	@PostMapping("/transfer-money")
	public ModelAndView transferMoney(@RequestParam("accNo")String accNo, @RequestParam("amount") String amount,Authentication authentication) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		transactionService.transferMoney(Integer.parseInt(amount), userPrincipal.getAccountNumber(), Integer.parseInt(accNo));
		modelAndView.setViewName("redirect:/user/home");
		return modelAndView;
	}

}
