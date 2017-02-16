package com.bardouski.ui.printer;

import java.util.List;

public interface IPrinter {

	void print(Object object);
	
	void print(List<Object> object);
	
	void printFail(String message);
	
}
