package com.bardouski.program.model;

public interface IMechanic {

	String getFullName();

	void setFullName(String fullName);

	IOrder getCurrentOrder();

	void setCurrentOrder(IOrder currentOrder);

	int getId();

	void setId(int id);

}
