package com.payel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payel.entities.CustomerBean;
import com.payel.entities.TransactionBean;

@Repository
public interface TransactionBeanRepository extends CrudRepository<TransactionBean, Integer> {

	public List<TransactionBean> findAllByUserId(CustomerBean accNo);
}
