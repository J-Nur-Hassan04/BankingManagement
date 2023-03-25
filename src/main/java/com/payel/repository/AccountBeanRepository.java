package com.payel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payel.entities.AccountBean;

@Repository
public interface AccountBeanRepository extends CrudRepository<AccountBean, Integer> {

}
