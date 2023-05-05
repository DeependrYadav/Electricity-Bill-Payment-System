package com.masai.service;

import java.util.List;

import com.masai.entities.Bills;
import com.masai.exceptions.BillsException;

public interface BillsService {

	public List<Bills> viewConsumerBills(String email, List<Bills> bills)throws BillsException;
	
	public void viewConsumerUnpaidBills(String email, List<Bills> bills)throws BillsException;
	
	public List<Bills> payConsumerBills(String email, List<Bills> bills)throws BillsException;
	
	public List<Bills> viewAllBills(List<Bills> bills) throws BillsException;
	
	public void payBill(String email, List<Bills> bills, double amt)throws BillsException;
	
}
