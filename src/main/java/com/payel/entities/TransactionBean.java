package com.payel.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "transaction_details")
public class TransactionBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tr_seq")
	@Column(name = "teansaction_id")
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private CustomerBean userId;

	@Column(name = "benefeciary_name")
	private String benefeciaryName;

	@ManyToOne
	@JoinColumn(name = "account_number")
	private AccountBean beneficiaryAccountNumber;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "blance_amount")
	private Integer balanceAmount;
	
	@Column(name = "transection_type")
	private String transectionType;
	
	public String getTransectionType() {
		return transectionType;
	}

	public void setTransectionType(String transectionType) {
		this.transectionType = transectionType;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@UpdateTimestamp
	@Column(name = "last_updated_at")
	private Timestamp lastUpdated;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public CustomerBean getUserId() {
		return userId;
	}

	public void setUserId(CustomerBean userId) {
		this.userId = userId;
	}

	public String getBenefeciaryName() {
		return benefeciaryName;
	}

	public void setBenefeciaryName(String benefeciaryName) {
		this.benefeciaryName = benefeciaryName;
	}

	public AccountBean getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(AccountBean beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Integer balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

}
