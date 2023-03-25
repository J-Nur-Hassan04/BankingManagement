package com.payel.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "customer_details")
public class CustomerBean {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
	private Integer userId;

	@Column(name = "user_name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "user_email", unique=true)
	private String email;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "adhar_no")
	private String adharNo;

	@OneToOne(mappedBy = "userId", cascade = CascadeType.ALL)
	@JoinColumn(name = "accountNumber")
	private AccountBean accountNumber;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public AccountBean getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(AccountBean accountNumber) {
		this.accountNumber = accountNumber;
	}

}
