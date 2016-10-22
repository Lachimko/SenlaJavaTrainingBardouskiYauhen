package bardouski.senla.training.controllers.stores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import bardouski.senla.training.comparators.OrderCompleteDateComparator;
import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.comparators.OrderStartDateComparator;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;
import bardouski.senla.training.model.WorkPlace;
import bardouski.senla.training.model.enums.OrderStatus;

public class OrderStore {

	private static final String DATE_WRONG_FORMAT = "Date entered in wrong format";
	private static List<Order> orders = new ArrayList<Order>();

	public void createOrder(Task task) {

		Mechanic mechanic = MechanicStore.findFreeMechanic();
		WorkPlace workplace = WorkPlaceStore.findFreePlace();
		Order order;

		if (mechanic == null) {
			System.out.println("No free mechanics right now.");
		} else if (workplace == null) {
			System.out.println("No free workplaces aviable.");
		} else {

			order = new Order(mechanic, workplace, task);
			mechanic.setCurrentOrder(order);
			workplace.setOrder(order);

			orders.add(order);
		}
	}

	public void removeOrder(int orderId) {

		Iterator<Order> iterator = orders.iterator();

		while (iterator.hasNext()) {

			if (iterator.next().getId() == orderId) {
				iterator.remove();
			}
		}
	}

	public void closeOrder(int orderId) {

		for (Order order : orders) {

			if (order.getId() == orderId) {
				order.setOrderStatus(OrderStatus.CLOSED);
			}
		}
	}

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

	public List<Order> ordersInProcess() {

		List<Order> inProcessOrders = new ArrayList<>();

		for (Order order : orders) {

			if (order.getOrderStatus() == OrderStatus.INPROGRESS) {

				inProcessOrders.add(order);
			}
		}
		return inProcessOrders;
	}

	public List<Order> ordersCompleted() {

		List<Order> completedOrders = new ArrayList<>();

		for (Order order : orders) {

			if (order.getOrderStatus() == OrderStatus.READY) {

				completedOrders.add(order);
			}
		}
		return completedOrders;
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

	/* GETTERS/SETTERS */
	public static List<Order> getOrders() {
		return orders;
	}

	public static void setOrders(List<Order> orders) {
		OrderStore.orders = orders;
	}

}
