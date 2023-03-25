package com.payel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payel.entities.AccountBean;
import com.payel.entities.CustomerBean;

@Repository
public interface CustomerBeanRepository extends CrudRepository<CustomerBean, Integer> {
	Optional<CustomerBean> findByAccountNumber(AccountBean accountNumber);

	Optional<CustomerBean> findByEmailIgnoreCase(String email);
}
