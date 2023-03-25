package com.payel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.payel.service.CustomerService;
import com.payel.service.TransactionService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TransactionService transactionService;

	@GetMapping("/create-customer")
	public ModelAndView getCreateCustomePage() {
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("CreateUser");
		return modelandview;
	}
	
	@PostMapping("/create-customer")
	public ModelAndView createCustome(@RequestParam("name") String name, @RequestParam("gender") String gender,
			@RequestParam("email") String email, @RequestParam("contact") String contact, @RequestParam("adhar") String adharNumber, @RequestParam("accountType") String accountType) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("userData", customerService.saveNewCustomer(name, gender, email, contact, adharNumber, accountType));
		modelandview.addObject("homeLink", "/admin/home");
		modelandview.setViewName("UserCreated");
		return modelandview;
	}
	
	@PostMapping("/edit-customer")
	public ModelAndView editCustome(@RequestParam("accNo")String accNo ,@RequestParam("name") String name, @RequestParam("gender") String gender,
			@RequestParam("email") String email, @RequestParam("contact") String contact, @RequestParam("adhar") String adharNumber) {
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("userData", customerService.saveModifiedCustomer(accNo, name, gender, email, contact, adharNumber));
		modelandview.addObject("homeLink", "/admin/home");
		modelandview.setViewName("UserCreated");
		return modelandview;
	}
	
	@PostMapping("/verify-customer")
	public ModelAndView getCoustomerDetails(@RequestParam("AcccountNumber")Integer accountNumber, @RequestParam("type")String type) throws AccountNotFoundException
	{
		Map<String, String> response = new HashMap<>();
		response = customerService.getCustomerDetails(accountNumber);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("data", response);
		modelAndView.addObject("accountNumber", response.get("Account Number"));
		modelAndView.addObject("type",type);
		if(type.equals("d")) {
		modelAndView.setViewName("DepositeForm");
		}else
		{
			modelAndView.setViewName("WithdrawFrom");
		}
		return modelAndView;
	}
	
	@PostMapping("/account-verify-page")
	public ModelAndView getWithdrawPage(@RequestParam("type")String type)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", type);
		modelAndView.setViewName("AccountVerifyPage");
		return modelAndView;
	}
	
	@PostMapping("/transection-success")
	public ModelAndView withdrawMoney(@RequestParam("amount")Integer amount, @RequestParam("AccountNumber") String accNo, @RequestParam("type")String type) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		if(type.equals("w")) {
		transactionService.withdrawMoney(amount, Integer.parseInt(accNo));
		}else if(type.equals("d"))
		{
			transactionService.depositeMoney(amount, Integer.parseInt(accNo));
		}
		modelAndView.addObject("data", customerService.getCustomerDetails(Integer.parseInt(accNo)));
		modelAndView.setViewName("TransectionSuccess");
		return modelAndView;
	}
	
	@GetMapping("edit-custome-details")
	public ModelAndView editCustomeDetails(@RequestParam("accNo")String accNo) throws AccountNotFoundException, NumberFormatException
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("data", customerService.getCustomerDetails(Integer.parseInt(accNo)));
		modelAndView.addObject("saveLink","/admin/edit-customer");
		modelAndView.addObject("homeLink", "/admin/home");
		modelAndView.setViewName("CustomeEditForm");
		return modelAndView;
	}
	
	@GetMapping("/home")
	public ModelAndView getAdminIndex()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminIndex");
		return modelAndView;
	}

}
