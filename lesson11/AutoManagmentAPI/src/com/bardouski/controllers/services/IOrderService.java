package com.bardouski.controllers.services;

import java.util.Date;
import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrderService {

	void addOrder(IOrder order);

	IOrder getOrder(int orderId) throws NoSuchObjectException;

	List<? extends IOrder> getAllOrders();
	
	List<? extends IOrder> getAllOrders(OrderStatus orderStatus);
	
	void replaceDatesOfOrdersFrom(int order, int howManyDays);

	void removeOrder(int order) throws NoSuchObjectException;

	List<? extends IWorkPlace> getFreePlacesInDate(Date date);

	List<? extends IOrder> sortOrdersByCompleteDateAction();
	
	List<? extends IOrder> sortOrdersByPriceAction();
	
	List<? extends IOrder> sortOrdersByRequestDateAction();
	
	List<? extends IOrder> sortOrdersByStartDateAction();
	
}
