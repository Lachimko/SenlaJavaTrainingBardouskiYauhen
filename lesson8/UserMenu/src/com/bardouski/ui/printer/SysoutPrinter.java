package com.bardouski.ui.printer;

import java.util.Collections;
import java.util.List;

public class SysoutPrinter implements IPrinter {

	@Override
	public void print(Object object) {
		
		if (object != null){
			
			Class<?> objectClass = object.getClass();
			
			if (objectClass == String.class){
				
				System.out.println(object);
			} else {
				
				System.out.println(object.toString());
			}
		} 
		
		
	}

	@Override
	public void print(List<Object> object) {

		if (!object.isEmpty()){
			
			for (Object item : object) {
				System.out.println(item.toString());
			}
		} else {
			System.out.println(Collections.emptyList());
		}
	
	}

	@Override
	public void printFail(String message) {
		System.err.println(message);
		
	}

}
