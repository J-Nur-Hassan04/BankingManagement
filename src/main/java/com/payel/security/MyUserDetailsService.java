
package com.payel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.payel.entities.User;
import com.payel.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User masterUser = new User();
		masterUser.setId(0);
		masterUser.setEmail("admin@gmail.com");
		masterUser.setName("Admin");
		masterUser.setPassword("pass");
		masterUser.setRole("Admin");
		masterUser.setCustomerBean(null);
		
		if(username.equals("admin@gmail.com"))
		{
			return new UserPrincipal(masterUser);
		}
		
		
		User user = userService.getUserByEmailId(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		
		
		return new UserPrincipal(user);
	}

}
