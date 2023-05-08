package com.masai.entities;

import java.io.Serializable;

public class Consumer implements Serializable {

	private String username;
	private String password;
	private String address;
	private String email;

	private Bills bill;

	public Consumer() {
		super();
	}

	public Consumer(String username, String password, String address, String email, Bills bill) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.email = email;
		this.bill = bill;
	}

	public Bills getBill() {
		return bill;
	}

	public void setBill(Bills bill) {
		this.bill = bill;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name = " + username + ",  Password = " + password + ",  Address=" + address + ",  Email=" + email+
				"\n---------------------------------------------------------------------------------";
	}

}
