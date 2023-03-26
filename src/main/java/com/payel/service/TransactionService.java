package com.payel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payel.entities.AccountBean;
import com.payel.entities.CustomerBean;
import com.payel.entities.TransactionBean;
import com.payel.repository.AccountBeanRepository;
import com.payel.repository.CustomerBeanRepository;
import com.payel.repository.TransactionBeanRepository;



@Service
public class TransactionService {
	@Autowired
	TransactionBeanRepository transactionBeanRepository;
	@Autowired
	AccountBeanRepository accountBeanRepository;
	@Autowired
	CustomerBeanRepository customerBeanRepository;
	
	public Boolean depositeMoney(Integer amount, Integer accNo,String transectionType) throws Exception
	{
		TransactionBean transactionBean = new TransactionBean();
		Optional<AccountBean> accNumber = accountBeanRepository.findById(accNo);
		if(null == accNumber.get())
		{
			throw new AccountNotFoundException("Account not found");
		}
		Optional<CustomerBean> custoOptional = customerBeanRepository.findByAccountNumber(accNumber.get());
		transactionBean.setUserId(custoOptional.get());
		transactionBean.setBenefeciaryName(custoOptional.get().getName());
		transactionBean.setBeneficiaryAccountNumber(accNumber.get());
		transactionBean.setAmount(amount);
		transactionBean.setBalanceAmount(accNumber.get().getBalanceAmount()+ amount);
		transactionBean.setTransectionType(transectionType);
		
		AccountBean deposite = accNumber.get();
		deposite.setBalanceAmount(accNumber.get().getBalanceAmount() + amount);
		
		accountBeanRepository.save(deposite);
		
		TransactionBean savedTransaction = transactionBeanRepository.save(transactionBean);
		if(null == savedTransaction)
		{
			throw new Exception("Transection faild");
		}
		return true;
	}
	
	public Boolean transferMoney(Integer amount,Integer senderAccNo, Integer receiverAccNo) throws Exception
	{
		Boolean withdrawSuccess = true;
		Boolean depositeSuccess = true;
			
			try {
				withdrawSuccess = withdrawMoney(amount, senderAccNo, "Money transfer");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				depositeSuccess = depositeMoney(amount, receiverAccNo,"Money transfer");
				
			} catch (Exception e) {
				depositeSuccess = false;
				System.out.println(e.getMessage());
			}
			if(!depositeSuccess)
			{
				depositeMoney(amount, senderAccNo, "Role back");
			}
			
		return true;
	}
	
	public Boolean withdrawMoney(Integer amount, Integer accNo, String transectionType) throws Exception
	{
		TransactionBean transactionBean = new TransactionBean();
		Optional<AccountBean> accNumber = accountBeanRepository.findById(accNo);
		if(null == accNumber.get())
		{
			throw new AccountNotFoundException("Account not found");
		}
		Optional<CustomerBean> custoOptional = customerBeanRepository.findByAccountNumber(accNumber.get());
		transactionBean.setUserId(custoOptional.get());
		transactionBean.setBenefeciaryName(custoOptional.get().getName());
		transactionBean.setBeneficiaryAccountNumber(accNumber.get());
		transactionBean.setAmount(amount);
		transactionBean.setTransectionType(transectionType);
		
		
		AccountBean withdraw = accNumber.get();
		if(withdraw.getBalanceAmount() - amount <0)
		{
			throw new Exception("Have not suficient balance");
		}
		transactionBean.setBalanceAmount(withdraw.getBalanceAmount() - amount);
		withdraw.setBalanceAmount(accNumber.get().getBalanceAmount() -amount);
		
		accountBeanRepository.save(withdraw);
		
		TransactionBean savedTransaction = transactionBeanRepository.save(transactionBean);
		if(null == savedTransaction)
		{
			throw new Exception("Transection faild");
		}
		return true;
	}
	
	public List<TransactionBean> getTransections(String email)
	{
		List<TransactionBean> transections = new ArrayList<>();
		Optional<CustomerBean> optCustomerBean = customerBeanRepository.findByEmailIgnoreCase(email);
		transections = transactionBeanRepository.findAllByUserId(optCustomerBean.get());
		return transections;
	}

}
