package com.jpmc.training.domain;

public class Account {
	private int account_no;
	private int customer_id;
	private String account_type;
	private double interest_rate;
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public Account(int account_no, int customer_id, String account_type) {
		super();
		this.account_no = account_no;
		this.customer_id = customer_id;
		this.account_type = account_type;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}
	
	
	

}
