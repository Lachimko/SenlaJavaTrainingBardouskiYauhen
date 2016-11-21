package com.bardouski.program.controllers.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.program.dbprocessor.serializator.FacadeResultContainer;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.program.model.enums.OrderStatus;

public interface IOrderService {

	void addOrder(Order order);

	Order getOrder(int orderId) throws NoSuchObjectException;

	List<Order> getAllOrders();
	
	List<Order> getAllOrders(OrderStatus orderStatus);
	
	void replaceDatesOfOrdersFrom(Order order, int howManyDays);

	void removeOrder(Order order) throws NoSuchObjectException;

	List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator);

	List<WorkPlace> getFreePlacesInDate(Date date, List<Garage> garages);

	void saveToFile(FacadeResultContainer resultContainer);

	/**Return new unique Id from OrderStore. Use for getting clone*/
	int getNextOrderId();
	
}
