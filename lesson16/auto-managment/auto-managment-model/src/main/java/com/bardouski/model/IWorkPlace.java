package com.bardouski.model;

public interface IWorkPlace extends EntityModel {

	IOrder getOrder();

	void setOrder(IOrder order);

	Integer getId();

	void setId(Integer id);

}
