package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import bardouski.senla.training.comparators.OrderStartDateComparator;
import bardouski.senla.training.controllers.stores.storeparent.Store;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.WorkPlace;
import bardouski.senla.training.model.enums.OrderStatus;

public class OrderStore extends Store {

	private List<Order> orders;

	// Constructors
	public OrderStore(DBProcessor dbProcessor) {

		if (Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}

		orders = dbProcessor.getOrdersDB();
		setOrdersLink(orders);
	}
	// END Constructors

	public void addOrder(Order order) {
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

	/* GETTERS/SETTERS */
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
