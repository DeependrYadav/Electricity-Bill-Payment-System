package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Bills implements Serializable {

	private String email;
	private double bill;
	private LocalDateTime date;
	private String category;

	public Bills(String email,double bill, LocalDateTime date,String category) {
		super();
		this.bill = bill;
		this.date = date;
		this.email = email;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Email = "+ email +", bill = " + bill + ", date = " + date + ", Bill Type = " + category+
				"\n---------------------------------------------------------------------------------";
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
