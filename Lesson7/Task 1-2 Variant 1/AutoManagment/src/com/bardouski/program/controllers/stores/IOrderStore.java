package com.bardouski.program.controllers.stores;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.program.model.enums.OrderStatus;

public interface IOrderStore {

	List<Order> getOrders();

	int getNextId();

	void addOrder(Order order);

	Order getOrder(int orderId) throws NoSuchObjectException;

	/** return copy of orders collection */
	List<Order> getAllOrders();

	/** return collection with same status */
	public List<Order> getAllOrders(OrderStatus orderStatus);

	/**
	 * @throws NoSuchObjectException
	 *             if collection didn't change
	 */
	void removeOrder(Order order) throws NoSuchObjectException;

	/** Rewrites start date and complete date forward for howManyDays */
	void replaceDatesOfOrdersFrom(Order order, int howManyDays);

	/**
	 * Return sorted collection of Orders by comparator translating in parameter
	 */
	List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator);

	/** return need orders */
	List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status);

	List<WorkPlace> getFreePlacesInDate(Date date, List<Garage> garages);

}
