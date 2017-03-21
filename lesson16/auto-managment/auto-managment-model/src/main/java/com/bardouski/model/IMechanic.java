package com.bardouski.model;

public interface IMechanic extends EntityModel {

	String getFullName();

	void setFullName(String fullName);

	IOrder getCurrentOrder();

	void setCurrentOrder(IOrder currentOrder);

	Integer getId();

	void setId(Integer id);

}
