package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Bills implements Serializable {

	private String email;
	private double bill;
	private LocalDateTime date;

	public Bills(String email,double bill, LocalDateTime date) {
		super();
		this.bill = bill;
		this.date = date;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Email = "+ email +" bill = " + bill + " date = " + date;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}


	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}


}
