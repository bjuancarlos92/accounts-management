package com.account.management.entity;

import java.util.Date;

public class Reporte {
	
	private String date;
	private String name;
	private String accountNumber;
	private String accountType;
	private Integer initialBalance;
	private String status;
	private Integer value;
	private Integer balance;
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public Integer getInitialBalance() {
		return initialBalance;
	}
	
	public void setInitialBalance(Integer initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public Integer getBalance() {
		return balance;
	}
	
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	public Reporte() {
		
	}

}
