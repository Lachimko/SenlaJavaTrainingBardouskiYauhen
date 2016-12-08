package com.bardouski.program.controllers.stores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.stores.IOrderStore;
import com.bardouski.exceptions.NoDBConnectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.program.comparators.OrderIdComparator;
import com.bardouski.program.comparators.OrderStartDateComparator;

public class OrderStore extends Store implements IOrderStore {

	private volatile Integer nextId;
	private List<Order> orders;

	public OrderStore() throws NoDBConnectionException, FileNotFoundException, ClassNotFoundException {

		if (Store.dbProcessor != null) {
			orders = dbProcessor.getOrdersDB();
			nextId = getIdCounterOfOrderStore();
		}
	}

	/**
	 * Return correct id after processing mechanic list. Not Synchronized
	 * method, cause call itself during initializn' store
	 */
	private int getIdCounterOfOrderStore() {

		if (!orders.isEmpty()) {
			List<Order> sortedList = new ArrayList<>(orders);
			Collections.sort(sortedList, new OrderIdComparator());
			return (sortedList.get(0).getId()) + 1;
		} else {
			return 1;
		}
	}

	@Override
	public List<Order> getOrders() {
		synchronized (orders) {
			return orders;
		}
	}

	@Override
	public int getNextId() {
		return nextId;
	}

	@Override
	public void addOrder(IOrder order) {
		synchronized (nextId) {
			order.setId(nextId);
			nextId++;
		}

		synchronized (orders) {
			orders.add((Order) order);
		}
	}

	@Override
	public Order getOrder(int orderId) throws NoSuchObjectException {

		synchronized (orders) {
			for (Order order : orders) {
				if (order.getId() == orderId) {
					return order;
				}
			}
		}
		throw new NoSuchObjectException();
	}

	/** return copy of orders collection */
	@Override
	public List<Order> getAllOrders() {
		synchronized (orders) {
			return orders;
		}
	}

	/** return collection with same status */
	@Override
	public List<Order> getAllOrders(OrderStatus orderStatus) {

		List<Order> list = new ArrayList<>();

		synchronized (orders) {
			for (Order order : orders) {
				if (order.getOrderStatus() == orderStatus) {
					list.add(order);
				}
			}
		}
		return list;
	}

	/**
	 * @throws NoSuchObjectException
	 *             if collection didn't change
	 */
	@Override
	public void removeOrder(IOrder order) throws NoSuchObjectException {

		synchronized (orders) {
			int size = orders.size();
			orders.remove(order);

			if (size == orders.size()) {
				throw new NoSuchObjectException();
			}
		}
	}

	/** Rewrites start date and complete date forward for howManyDays */
	@Override
	public void replaceDatesOfOrdersFrom(IOrder order, int howManyDays) {

		List<Order> list = returnOrdersSortedByComparator(new OrderStartDateComparator());

		Calendar cal = Calendar.getInstance();
		boolean flag = false;

		synchronized (orders) {

			for (Order orderItem : list) {

				// Find delayed order and set new complete date
				if (orderItem.equals(order)) {
					flag = true;
					cal.setTime(orderItem.getTask().getCompleteDate());
					cal.add(Calendar.DAY_OF_MONTH, howManyDays);
					orderItem.getTask().setCompleteDate(cal.getTime());
					continue;
				}
				if (flag) {
					// rewrite start date to forwarded date
					cal.setTime(orderItem.getTask().getStartDate());
					cal.add(Calendar.DAY_OF_MONTH, howManyDays);
					orderItem.getTask().setStartDate(cal.getTime());

					// rewrite complete date to forwarded date
					cal.setTime(orderItem.getTask().getCompleteDate());
					cal.add(Calendar.DAY_OF_MONTH, howManyDays);
					orderItem.getTask().setCompleteDate(cal.getTime());
				}
			}
			orders = list;

		}
	}

	/**
	 * Return sorted collection of Orders by comparator translating in parameter
	 */
	@Override
	public List<Order> returnOrdersSortedByComparator(Comparator<IOrder> comparator) {

		List<Order> list;
		synchronized (orders) {
			list = new ArrayList<>(orders);
		}

		Collections.sort(list, comparator);
		return list;

	}

	/** return need orders */
	public List<Order> getOrdersByStatus(List<IOrder> orders, OrderStatus status) {

		List<Order> statusOrders = new ArrayList<>();

		synchronized (orders) {
			for (IOrder order : orders) {
				if (order.getOrderStatus() == status) {
					statusOrders.add((Order) order);
				}
			}
		}
		return statusOrders;
	}

	@Override
	public List<WorkPlace> getFreePlacesInDate(Date date, List<? extends IGarage> garages) {

		List<WorkPlace> list = new ArrayList<>();

		for (IGarage garage : garages) {
			for (IWorkPlace workPlace : garage.getWorkPlaces()) {

				if (workPlace.getOrder() == null) {
					list.add((WorkPlace) workPlace);
				} else if (workPlace.getOrder().getTask().getCompleteDate().before(date)
						&& workPlace.getOrder().getTask().getStartDate().after(date)) {
					list.add((WorkPlace) workPlace);
				}
			}
		}
		return list;
	}

}
