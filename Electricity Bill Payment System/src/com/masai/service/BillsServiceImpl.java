package com.masai.service;

import java.util.ArrayList;
import java.util.List;

import com.masai.entities.Bills;
import com.masai.exceptions.BillsException;

public class BillsServiceImpl implements BillsService {

	@Override
	public List<Bills> viewConsumerBills(String email, List<Bills> bills) throws BillsException {
		List<Bills> myTr = new ArrayList<>();
		boolean ans = false;
		for (Bills tr : bills) {
			if (tr.getEmail().equals(email)) {
//				myTr.add(tr);
				ans = true;
				System.out.println(tr);
			}
		}
		if (ans)
			return myTr;
		else {
			throw new BillsException("You haven't pay any bill");
		}
	}

	public List<Bills> payConsumerBills(String email, List<Bills> bills) throws BillsException {
		// TODO Auto-generated method stub
		List<Bills> myTr = new ArrayList<>();
		boolean ans = false;
		for (Bills tr : bills) {
			if (tr.getEmail().equals(email) && tr.getBill() > 1) {
//				myTr.add(tr);
				ans = true;
				System.out.println(tr);
			}
		}
		if (ans)
			return myTr;
		else {
			throw new BillsException("You already paid your bill");
		}
	}

	@Override
	public List<Bills> viewAllBills(List<Bills> bills) throws BillsException {
		// TODO Auto-generated method stub
		if (bills != null && bills.size() > 0) {
			return bills;
		} else {
			throw new BillsException("No bill done yet");
		}
	}

	@Override
	public void payBill(String email, List<Bills> bills, double amt) throws BillsException {
		// TODO Auto-generated method stub

		boolean ans = false;
		for (Bills tr : bills) {
			if (tr.getEmail().equals(email) && tr.getBill() > 0) {
				double paid = tr.getBill() - amt;
				tr.setBill(paid);
				ans = true;
			}
		}
		if (ans) {
			System.out.println("Bill paid successfully");
		} else {
			System.out.println("You already paid your bill");
		}

	}

	@Override
	public void viewConsumerUnpaidBills(String email, List<Bills> bills) throws BillsException {
		// TODO Auto-generated method stub
		boolean ans = false;
		for (Bills tr : bills) {
			if (tr.getEmail().equals(email) && tr.getBill() > 1) {
//				myTr.add(tr);
				ans = true;
				System.out.println(tr);
			}
		}
		if (!ans)
			throw new BillsException("You already paid your bill");
	}

}
