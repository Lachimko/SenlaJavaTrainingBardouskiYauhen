package com.bardouski.model;

import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrder {

	int getId();

	void setId(int id);

	OrderStatus getOrderStatus();

	void setOrderStatus(OrderStatus orderStatus);

	IMechanic getMechanic();

	void setMechanic(IMechanic mechanic);

	IWorkPlace getWorkPlace();

	void setWorkPlace(IWorkPlace workPlace);

	ITask getTask();

	void setTask(ITask task);

	Object clone() throws CloneNotSupportedException;

}
