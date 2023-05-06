package com.masai.service;

import java.util.Map;

import com.masai.entities.Complain;
import com.masai.exceptions.InvalidCredentialsException;

public class ComplainServiceImpl implements ComplainService{

	@Override
	public void raiseComplain(Complain comp, Map<Integer,Complain> complain) {
		// TODO Auto-generated method stub
		int id = comp.getComplainId();
		complain.put(id, comp);
	}

	@Override
	public void viewComplain(int complainId, Map<Integer, Complain> complain) throws InvalidCredentialsException {
		
		if(complain.containsKey(complainId)) {
//			String str = complain.get(complainId);
//			System.out.println("kasgkjgfjksf");
			System.out.println(complain.get(complainId));
		}else {
			throw new InvalidCredentialsException("Please write valid Compalin Id");
		}
	}

	@Override
	public void updateStatusOfComplain(int complainId, String str, Map<Integer, Complain> complain)
			throws InvalidCredentialsException {
		if(complain.containsKey(complainId)) {
			complain.get(complainId).setStatus(str);
		}else {
			throw new InvalidCredentialsException("Please write valid Compalin Id");
		}
		
	}

	@Override
	public void viewAllComplain(Map<Integer, Complain> complain) {
		
		for(Map.Entry<Integer, Complain> comp : complain.entrySet()) {
			System.out.println(comp);
		}
		
	}

}
