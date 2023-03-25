package com.payel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payel.entities.CustomerBean;
import com.payel.entities.User;
import com.payel.repository.CustomerBeanRepository;
import com.payel.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerBeanRepository customerBeanRepository;
	
	public User getUserByEmailId(String email)
	{
		
		User user = userRepository.findByEmailIgnoreCase(email);
		return user;
	}
}
