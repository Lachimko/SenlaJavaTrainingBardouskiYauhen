package com.bardouski.program.controllers.stores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.program.comparators.OrderIdComparator;
import com.bardouski.program.comparators.OrderStartDateComparator;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.program.model.enums.OrderStatus;

public class OrderStore extends Store implements IOrderStore{

	private int nextId;
	private List<Order> orders;

	// Constructors, Getters/Setters
	public OrderStore(DBProcessor dbProcessor) {

		if (Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}
		
		orders = dbProcessor.getOrdersDB();

		if (!orders.isEmpty()) {
			List<Order> sortedList = new ArrayList<>(orders);
			Collections.sort(sortedList, new OrderIdComparator());
			nextId = (sortedList.get(0).getId()) + 1;
		} else {
			nextId = 1;
		}
	}
	
	public OrderStore(){
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public int getNextId() {
		return nextId;
	}
	// END Constructors, Getters/Setters

	public void addOrder(Order order) {
		order.setId(nextId);
		nextId++;
		orders.add(order);
	}

	public Order getOrder(int orderId) throws NoSuchObjectException {

		for (Order order : orders) {
			if (order.getId() == orderId) {
				return order;
			}
		}
		throw new NoSuchObjectException();
	}

	/** return copy of orders collection */
	public List<Order> getAllOrders() {
		return new ArrayList<>(orders);
	}

	/** return collection with same status */
	public List<Order> getAllOrders(OrderStatus orderStatus) {

		List<Order> list = new ArrayList<>();

		for (Order order : orders) {
			if (order.getOrderStatus() == orderStatus) {
				list.add(order);
			}
		}
		return list;
	}

	/**
	 * @throws NoSuchObjectException
	 *             if collection didn't change
	 */
	public void removeOrder(Order order) throws NoSuchObjectException {

		int size = orders.size();
		orders.remove(order);

		if (size == orders.size()) {
			throw new NoSuchObjectException();
		}
	}

	/** Rewrites start date and complete date forward for howManyDays */
	public void replaceDatesOfOrdersFrom(Order order, int howManyDays) {

		List<Order> list = returnOrdersSortedByComparator(new OrderStartDateComparator());
		Calendar cal = Calendar.getInstance();
		boolean flag = false;

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

	/**
	 * Return sorted collection of Orders by comparator translating in parameter
	 */
	public List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator) {

		List<Order> list = new ArrayList<>(orders);
		Collections.sort(list, comparator);
		return list;

	}

	/** return need orders */
	public List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status) {

		List<Order> statusOrders = new ArrayList<>();

		for (Order order : orders) {
			if (order.getOrderStatus() == status) {
				statusOrders.add(order);
			}
		}
		return statusOrders;
	}

	public List<WorkPlace> getFreePlacesInDate(Date date, List<Garage> garages) {

		List<WorkPlace> list = new ArrayList<>();

		for (Garage garage : garages) {
			for (WorkPlace workPlace : garage.getWorkPlaces()) {

				if (workPlace.getOrder() == null) {
					list.add(workPlace);
				} else if (workPlace.getOrder().getTask().getCompleteDate().before(date)
						&& workPlace.getOrder().getTask().getStartDate().after(date)) {
					list.add(workPlace);
				}
			}
		}
		return list;
	}

}
