package bardouski.senla.training.controllers.stores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import bardouski.senla.training.comparators.OrderCompleteDateComparator;
import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.comparators.OrderStartDateComparator;
import bardouski.senla.training.controllers.stores.storeparent.Store;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;
import bardouski.senla.training.model.WorkPlace;
import bardouski.senla.training.model.enums.OrderStatus;

public class OrderStore extends Store {

	private static final String DATE_WRONG_FORMAT = "Date entered in wrong format";

	private List<Order> orders;

	public OrderStore(DBProcessor dbProcessor) {

		if (Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}

		orders = dbProcessor.getOrdersDB();
		setOrdersLink(orders);
	}

	public Order createOrder(Task task, Mechanic mechanic, WorkPlace workPlace) {
		return new Order(mechanic, workPlace, task);
	}

	public Order getOrder(int orderId) throws NoSuchObjectException {

		for (Order order : orders) {
			if (order.getId() == orderId) {
				return order;
			}
		}
		throw new NoSuchObjectException();
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	// public void closeOrder(Order order) {
	//
	// for (Order listOrder : orders) {
	//
	// if (listOrder.equals(order)) {
	// order.setOrderStatus(OrderStatus.CLOSED);
	// }
	// }
	// }

	public void cancelOrder(int orderId) {

		for (Order order : orders) {

			if (order.getId() == orderId) {
				order.setOrderStatus(OrderStatus.CANCELLED);
			}
		}
	}

	public void replaceDatesOfOrdersFrom(int delayedOrderId, int howManyDays) {

		List<Order> list = sortByStartDate();
		Calendar cal = Calendar.getInstance();
		boolean flag = false;

		for (Order order : list) {

			if (order.getId() == delayedOrderId) {
				flag = true;
				cal.setTime(order.getTask().getCompleteDate());
				cal.add(Calendar.DAY_OF_MONTH, howManyDays);
				Date completeDate = cal.getTime();
				order.getTask().setCompleteDate(completeDate);
				continue;
			}
			if (flag) {

				cal.setTime(order.getTask().getStartDate());
				cal.add(Calendar.DAY_OF_MONTH, howManyDays);
				Date startDate = cal.getTime();

				cal.setTime(order.getTask().getCompleteDate());
				cal.add(Calendar.DAY_OF_MONTH, howManyDays);
				Date completeDate = cal.getTime();

				order.getTask().setStartDate(startDate);
				order.getTask().setCompleteDate(completeDate);
			}
		}

		orders = list;
	}

	public List<Order> sortByRequestDate() {

		List<Order> list = new ArrayList<>(orders);

		Collections.sort(list, new OrderRequestDateComparator());

		return list;
	}

	public List<Order> sortByStartDate() {

		List<Order> list = new ArrayList<>(orders);

		Collections.sort(list, new OrderStartDateComparator());

		return list;
	}

	public List<Order> sortByCompleteDate() {

		List<Order> list = new ArrayList<>(orders);

		Collections.sort(list, new OrderCompleteDateComparator());

		return list;
	}

	public List<Order> sortByPrice() {

		List<Order> list = new ArrayList<>(orders);

		Collections.sort(list, new OrderPriceComparator());

		return list;
	}

	public List<Order> ordersInProcess(List<Order> orders) {

		List<Order> inProcessOrders = new ArrayList<>();

		for (Order order : orders) {

			if (order.getOrderStatus() == OrderStatus.INPROGRESS) {

				inProcessOrders.add(order);
			}
		}
		return inProcessOrders;
	}

	public List<Order> ordersReady(List<Order> orders) {

		List<Order> readyOrders = new ArrayList<>();

		for (Order order : orders) {

			if (order.getOrderStatus() == OrderStatus.READY) {

				readyOrders.add(order);
			}
		}
		return readyOrders;
	}

	public List<Order> ordersCompleted(List<Order> list) {

		List<Order> completedOrders = new ArrayList<>();

		for (Order order : list) {

			if (order.getOrderStatus() == OrderStatus.READY) {

				completedOrders.add(order);
			}
		}
		return completedOrders;
	}

	public List<Order> ordersDeleted(List<Order> orders) {

		List<Order> deletedOrders = new ArrayList<>();

		for (Order order : orders) {

			if (order.getOrderStatus() == OrderStatus.CLOSED) {

				deletedOrders.add(order);
			}
		}
		return deletedOrders;
	}

	public List<Order> ordersCancelled(List<Order> orders) {

		List<Order> cancelledOrders = new ArrayList<>();

		for (Order order : orders) {

			if (order.getOrderStatus() == OrderStatus.CANCELLED) {

				cancelledOrders.add(order);
			}
		}
		return cancelledOrders;
	}

	public Mechanic returnMechanicOfOrder(int orderId) {

		for (Order order : orders) {
			if (order.getId() == orderId) {
				return order.getMechanic();
			}
		}
		return null;
	}

	public List<Order> returnOrdersBetweenRequestDate(String fromDateXXXMMDD, String toDateXXXMMDD) {

		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("dd.MM.yyyy");

		Date startDate = null;
		Date completeDate = null;

		try {
			startDate = format.parse(fromDateXXXMMDD);
			completeDate = format.parse(toDateXXXMMDD);
		} catch (ParseException e) {
			System.out.println(DATE_WRONG_FORMAT);
			return null;
		}

		List<Order> list = new ArrayList<>();

		for (Order order : orders) {
			if (order.getTask().getStartDate().after(startDate)
					&& order.getTask().getStartDate().before(completeDate)) {

				list.add(order);
			}
		}
		return list;
	}

	public List<Order> returnOrdersBetweenCompleteDate(String fromDateXXXMMDD, String toDateXXXMMDD) {

		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("dd.MM.yyyy");

		Date startDate = null;
		Date completeDate = null;

		try {
			startDate = format.parse(fromDateXXXMMDD);
			completeDate = format.parse(toDateXXXMMDD);
		} catch (ParseException e) {
			System.out.println(DATE_WRONG_FORMAT);
			return null;
		}

		List<Order> list = new ArrayList<>();

		for (Order order : orders) {
			if (order.getTask().getCompleteDate().after(startDate)
					&& order.getTask().getCompleteDate().before(completeDate)) {

				list.add(order);
			}
		}
		return list;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	/* GETTERS/SETTERS */
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
