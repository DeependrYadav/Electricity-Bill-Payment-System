package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.entities.Consumer;
import com.masai.exceptions.DuplicateCredentialsException;
import com.masai.exceptions.InvalidCredentialsException;
import com.masai.exceptions.BillsException;

public interface ConsumerService {

	public boolean login(String email, String password, Map<String,Consumer> consumer) throws InvalidCredentialsException;
	
	public void signUp(Consumer consu, Map<String,Consumer> consumer)throws DuplicateCredentialsException;
	
	public List<Consumer> viewAllConsumer(Map<String, Consumer> consumer) throws BillsException;
}
