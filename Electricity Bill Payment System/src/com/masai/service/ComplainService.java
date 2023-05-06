package com.masai.service;

import java.util.Map;

import com.masai.entities.Complain;
import com.masai.exceptions.InvalidCredentialsException;

public interface ComplainService {

	public void raiseComplain(Complain comp, Map<Integer,Complain> complain);
	
	public void viewComplain(int complainId,Map<Integer,Complain> complain) throws InvalidCredentialsException;
	
	public void updateStatusOfComplain(int complainId,String str ,Map<Integer,Complain> complain) throws InvalidCredentialsException;
	
	public void viewAllComplain(Map<Integer,Complain> complain);
}
