package com.masai.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.masai.entities.Consumer;
import com.masai.exceptions.DuplicateCredentialsException;
import com.masai.exceptions.InvalidCredentialsException;
import com.masai.exceptions.BillsException;

public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public boolean login(String email, String password, Map<String, Consumer> consumer)
			throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		if (consumer.containsKey(email)) {
			if (consumer.get(email).getPassword().equals(password)) {
				return true;
			} else {
				throw new InvalidCredentialsException("Invalid Password");
			}
		} else {
			throw new InvalidCredentialsException("Don't have account on this mail, Plesae signup first");
		}
	}

	@Override
	public void signUp(Consumer consu, Map<String, Consumer> consumer) throws DuplicateCredentialsException {
		// TODO Auto-generated method stub
		if (consumer.containsKey(consu.getEmail())) {
			throw new DuplicateCredentialsException("Already have a account with this email");
		} else {
			consumer.put(consu.getEmail(), consu);
		}
	}

	@Override
	public List<Consumer> viewAllConsumer(Map<String, Consumer> consumer) throws BillsException {
		// TODO Auto-generated method stub
		List<Consumer> consumerData = null;
		if (consumer != null && consumer.size() > 0) {
			Collection<Consumer> coll = consumer.values();
			consumerData = new ArrayList<>(coll);

			return consumerData;
		} else {
			throw new BillsException("Consumer list is empty");
		}
	}

}
