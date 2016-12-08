package com.bardouski.exceptions;

@SuppressWarnings("serial")
public class NoDBConnectionException extends Exception{

	private static final String MESSAGE = "ERROR: No success DB connections available.\n";

	public String getMessage(){
		return MESSAGE;
	}
}
