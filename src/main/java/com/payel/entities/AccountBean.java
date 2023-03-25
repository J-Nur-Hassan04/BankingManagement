package com.payel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_DETAILS")
public class AccountBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_SEQ")
	@Column(name = "ACCOUNT_NUMBER")
	private Integer accountNumber;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "COUSTOMER_NAME")
	private String name;

	@Column(name = "ACCOUNT_BALANCE")
	private Integer balanceAmount;

	@OneToOne
	@PrimaryKeyJoinColumn
	private CustomerBean userId;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Integer balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public CustomerBean getUserId() {
		return userId;
	}

	public void setUserId(CustomerBean userId) {
		this.userId = userId;
	}

}
