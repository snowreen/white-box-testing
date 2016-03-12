package com.whitebox.testing.customer;

public class Customer {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private boolean isMember;
	private boolean isTaxExempt;
	
	public Customer(int customerId, String firstName, String lastName,
			boolean isMember, boolean isTaxExempt) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isMember = isMember;
		this.isTaxExempt = isTaxExempt;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	public boolean isTaxExempt() {
		return isTaxExempt;
	}
	public void setTaxExempt(boolean isTaxExempt) {
		this.isTaxExempt = isTaxExempt;
	}
	
	
	

}
