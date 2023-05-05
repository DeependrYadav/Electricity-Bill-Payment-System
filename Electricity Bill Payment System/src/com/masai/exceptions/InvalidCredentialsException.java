package com.masai.exceptions;

public class InvalidCredentialsException extends Exception {
	
	public InvalidCredentialsException() {
		
	}

	public InvalidCredentialsException(String msg){
		super(msg);
	}
}
