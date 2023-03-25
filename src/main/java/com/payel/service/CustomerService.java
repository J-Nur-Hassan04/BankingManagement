package com.payel.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payel.entities.AccountBean;
import com.payel.entities.CustomerBean;
import com.payel.entities.User;
import com.payel.repository.AccountBeanRepository;
import com.payel.repository.CustomerBeanRepository;
import com.payel.repository.UserRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerBeanRepository customerBeanRepository;
	
	@Autowired
	private AccountBeanRepository accountBeanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User saveNewCustomer(String name, String gender, String email, String contact, String adhar,String accountType) {

		User user = new User();
		CustomerBean customer = new CustomerBean();
		customer.setName(name);
		customer.setGender(gender);
		customer.setEmail(email);
		customer.setContactNumber(contact);
		customer.setAdharNo(adhar);
		
		AccountBean account = new AccountBean();
		account.setAccountType(accountType);
		account.setName(name);
		account.setBalanceAmount(0);
		AccountBean newAccount = accountBeanRepository.save(account);
		
	    customer.setAccountNumber(newAccount);
	    
	    CustomerBean newCustomer = customerBeanRepository.save(customer);
	    user.setId(newCustomer.getUserId());
	    user.setEmail(email);
	    user.setName(name);
	    user.setPassword(generatePassword(10));
	    user.setRole("User");
	    user.setCustomerBean(newCustomer);
	    
	    User newUser = userRepository.save(user);
	    
	    return newUser;
		
		
	}
	
	public User saveModifiedCustomer(String accNo ,String name, String gender, String email, String contact, String adhar) {

		Optional<AccountBean> optAccount = accountBeanRepository.findById(Integer.parseInt(accNo));
		Optional<CustomerBean> optCustomer = customerBeanRepository.findByAccountNumber(optAccount.get());
		User user = userRepository.findByCustomerBean(optCustomer.get());
		CustomerBean customer = optCustomer.get();
		customer.setName(name);
		customer.setGender(gender);
		customer.setEmail(email);
		customer.setContactNumber(contact);
		customer.setAdharNo(adhar);
		
		AccountBean account = optAccount.get();
		account.setName(name);
		AccountBean newAccount = accountBeanRepository.save(account);
	    customer.setAccountNumber(newAccount);
	    
	    CustomerBean newCustomer = customerBeanRepository.save(customer);
	    
	    user.setCustomerBean(newCustomer);
	    
	    User newUser = userRepository.save(user);
	    
	    return newUser;
		
		
	}


	public Map<String, String> getCustomerDetails(Integer accountNumber) throws AccountNotFoundException {
		Optional<AccountBean> account = accountBeanRepository.findById(accountNumber);
		if(!account.isPresent())
		{
			throw new AccountNotFoundException("Account nuber not found");
		}
		Optional<CustomerBean> customer = customerBeanRepository.findByAccountNumber(account.get());
		if(customer.isPresent())
		{
			Map<String, String> data = new TreeMap<String, String>();
			data.put("Name", customer.get().getName());
			data.put("Account Number", customer.get().getAccountNumber().getAccountNumber().toString());
			data.put("Adhar Number", customer.get().getAdharNo());
			data.put("Contact Number", customer.get().getContactNumber());
			data.put("User Email", customer.get().getEmail());
			data.put("Gender", customer.get().getGender());
			data.put("Balance", customer.get().getAccountNumber().getBalanceAmount().toString());
			return data;
		}
		return null;
	}
	
	private String generatePassword(int len)
    {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
                String symbols = "!@#$%^&*_=+-/.?<>)";
  
  
        String values = Capital_chars + Small_chars +
                        numbers + symbols;
        Random rndm_method = new Random();
  
        char[] password = new char[len];
  
        for (int i = 0; i < len; i++)
        {
            password[i] =
              values.charAt(rndm_method.nextInt(values.length()));
  
        }
        return password.toString();
    }

}
