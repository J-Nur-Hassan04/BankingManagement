package com.payel.repository;

import org.springframework.data.repository.CrudRepository;

import com.payel.entities.CustomerBean;
import com.payel.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByCustomerBean(CustomerBean bean);
	public User findByEmailIgnoreCase(String email);
}
