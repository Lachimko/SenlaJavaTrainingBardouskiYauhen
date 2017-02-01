package com.bardouski.controllers.stores;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

@Deprecated
public interface IOrderStore extends IStore{

	List<? extends IOrder> getOrders();

	int getNextId();

	void addOrder(IOrder order);

	IOrder getOrder(int orderId) throws NoSuchObjectException;

	/** return copy of orders collection */
	List<? extends IOrder> getAllOrders();

	/** return collection with same status */
	public List<? extends IOrder> getAllOrders(OrderStatus orderStatus);

	/**
	 * @throws NoSuchObjectException
	 *             if collection didn't change
	 */
	void removeOrder(IOrder order) throws NoSuchObjectException;

	/** Rewrites start date and complete date forward for howManyDays */
	void replaceDatesOfOrdersFrom(IOrder order, int howManyDays);

	/**
	 * Return sorted collection of Orders by comparator translating in parameter
	 */
	List<? extends IOrder> returnOrdersSortedByComparator(Comparator<IOrder> comparator);

	/** return need orders */
	List<? extends IOrder> getOrdersByStatus(List<IOrder> orders, OrderStatus status);

	List<? extends IWorkPlace> getFreePlacesInDate(Date date, List<? extends IGarage> garages);

}
