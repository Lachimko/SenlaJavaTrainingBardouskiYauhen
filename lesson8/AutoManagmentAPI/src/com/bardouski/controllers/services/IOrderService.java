package com.bardouski.controllers.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.enums.OrderStatus;

public interface IOrderService {

	void addOrder(IOrder order);

	IOrder getOrder(int orderId) throws NoSuchObjectException;

	List<? extends IOrder> getAllOrders();
	
	List<? extends IOrder> getAllOrders(OrderStatus orderStatus);
	
	void replaceDatesOfOrdersFrom(IOrder order, int howManyDays);

	void removeOrder(IOrder order) throws NoSuchObjectException;

	List<? extends IOrder> returnOrdersSortedByComparator(Comparator<IOrder> comparator);

	List<? extends IWorkPlace> getFreePlacesInDate(Date date, List<? extends IGarage> garages);

	void saveToFile(IResultContainer resultContainer);

	/**Return new unique Id from OrderStore. Use for getting clone*/
	int getNextOrderId();
	
}
