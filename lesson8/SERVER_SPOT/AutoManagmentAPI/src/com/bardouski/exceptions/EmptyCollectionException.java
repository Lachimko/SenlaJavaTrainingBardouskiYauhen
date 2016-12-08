package com.bardouski.exceptions;

@SuppressWarnings("serial")
public class EmptyCollectionException extends Exception{

	private static final String MESSAGE = "ERROR: You trying to process empty collection\n";

	public String getMessage(){
		return MESSAGE;
	}
}
