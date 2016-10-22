package bardouski.senla.training.controllers.services;

import java.util.Collections;
import java.util.List;

import bardouski.senla.training.comparators.OrderCompleteDateComparator;
import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.controllers.stores.OrderStore;
import bardouski.senla.training.controllers.stores.TaskStore;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;

public class OrderService {

	private OrderStore orderStore = new OrderStore();
	private TaskStore taskStore = new TaskStore();

	public void createOrder(String toDo, String startDateYYYY_MM_DD, String completeDateYYYY_MM_DD, double price) {

		Task task = taskStore.createTask(toDo, startDateYYYY_MM_DD, completeDateYYYY_MM_DD, price);

		if (task != null) {
			orderStore.createOrder(task);
		}
	}

	public void replaceDatesOfOrdersFrom(int delayedOrderId, int howManyDays) {
		orderStore.replaceDatesOfOrdersFrom(delayedOrderId, howManyDays);
	}

	public void removeOrder(int orderId) {
		orderStore.removeOrder(orderId);
	}

	public void closeOrder(int orderId) {
		orderStore.closeOrder(orderId);
	}

	public void cancelOrder(int orderId) {
		orderStore.cancelOrder(orderId);
	}

	public List<Order> sortOrderByRequestDate() {
		return orderStore.sortByRequestDate();
	}

	public List<Order> sortOrderByCompleteDate() {
		return orderStore.sortByCompleteDate();
	}

	public List<Order> sortOrderByStartDate() {
		return orderStore.sortByStartDate();
	}

	public List<Order> sortOrderByPrice() {
		return orderStore.sortByPrice();
	}

	public List<Order> ordersInProcess() {
		return orderStore.ordersInProcess();
	}

	public List<Order> viewOrdersInProcessSortedByRequestDate() {
		List<Order> inProcessOrders = orderStore.ordersInProcess();
		Collections.sort(inProcessOrders, new OrderRequestDateComparator());

		return inProcessOrders;
	}

	public List<Order> viewOrdersInProcessSortedByCompleteDate() {
		List<Order> inProcessOrders = orderStore.ordersInProcess();
		Collections.sort(inProcessOrders, new OrderCompleteDateComparator());

		return inProcessOrders;
	}

	public List<Order> viewOrdersInProcessSortedByPrice() {
		List<Order> inProcessOrders = orderStore.ordersInProcess();
		Collections.sort(inProcessOrders, new OrderPriceComparator());

		return inProcessOrders;
	}

	public Mechanic returnMechanicOfOrder(int orderId) {
		return orderStore.returnMechanicOfOrder(orderId);
	}

	public List<Order> returnOrdersBetweenRequestDate(String fromDateXXXMMDD, String toDateXXXMMDD) {
		return orderStore.returnOrdersBetweenRequestDate(fromDateXXXMMDD, toDateXXXMMDD);
	}

	public List<Order> returnOrdersBetweenCompleteDate(String fromDateYYYY_MM_DD, String toDateYYYY_MM_DD) {
		return orderStore.returnOrdersBetweenCompleteDate(fromDateYYYY_MM_DD, toDateYYYY_MM_DD);
	}

	public List<Order> returnCompleteOrdersBetweenCompleteDate(String fromDateYYYY_MM_DD, String toDateYYYY_MM_DD) {

		List<Order> list = orderStore.returnOrdersBetweenCompleteDate(fromDateYYYY_MM_DD, toDateYYYY_MM_DD);
		return orderStore.ordersCompleted(list);
	}

}
