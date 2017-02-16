package com.bardouski.controllers.services;

import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrderService {

	void addOrder(ITask task, Mechanic mechanic, WorkPlace workPlace);
	
	void addOrder(IOrder order);

	IOrder getOrder(int orderId) throws NoSuchObjectException;

	List<? extends IOrder> getAllOrders();
	
	List<? extends IOrder> getAllOrders(OrderStatus orderStatus);
	
	void replaceDatesOfOrdersFrom(int order, int howManyDays);

	void removeOrder(int order) throws NoSuchObjectException;

	List<? extends IOrder> sortOrdersByCompleteDateAction();
	
	List<? extends IOrder> sortOrdersByPriceAction();
	
	List<? extends IOrder> sortOrdersByRequestDateAction();
	
	List<? extends IOrder> sortOrdersByStartDateAction();
	
}
