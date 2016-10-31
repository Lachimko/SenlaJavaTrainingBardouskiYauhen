package bardouski.senla.training.controllers.services;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import bardouski.senla.training.comparators.OrderCompleteDateComparator;
import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.controllers.stores.OrderStore;
import bardouski.senla.training.controllers.stores.TaskStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;
import bardouski.senla.training.model.WorkPlace;

public class OrderService {

	private OrderStore orderStore;
	private TaskStore taskStore;

	public OrderService(DBProcessor dbProcessor) {

		taskStore = new TaskStore();
		orderStore = new OrderStore(dbProcessor);

	}

	public void addOrder(Order order) {
		orderStore.addOrder(order);
	}

	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderStore.getOrder(orderId);
	}
	
	public Order createOrder(Mechanic mechanic, WorkPlace workPlace, Task task) {
		return orderStore.createOrder(task, mechanic, workPlace);
	}

	public void replaceDatesOfOrdersFrom(int delayedOrderId, int howManyDays) {
		orderStore.replaceDatesOfOrdersFrom(delayedOrderId, howManyDays);
	}

	public void removeOrder(Order order) {
		orderStore.removeOrder(order);
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

	public List<Order> ordersInProcess(List<Order> orders) {
		return orderStore.ordersInProcess(orders);
	}

//	public List<Order> viewOrdersInProcessSortedByRequestDate() {
//		List<Order> inProcessOrders = orderStore.ordersInProcess();
//		Collections.sort(inProcessOrders, new OrderRequestDateComparator());
//
//		return inProcessOrders;
//	}
//
//	public List<Order> viewOrdersInProcessSortedByCompleteDate() {
//		List<Order> inProcessOrders = orderStore.ordersInProcess();
//		Collections.sort(inProcessOrders, new OrderCompleteDateComparator());
//
//		return inProcessOrders;
//	}
//
//	public List<Order> viewOrdersInProcessSortedByPrice() {
//		List<Order> inProcessOrders = orderStore.ordersInProcess();
//		Collections.sort(inProcessOrders, new OrderPriceComparator());
//
//		return inProcessOrders;
//	}

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

	public Task createTask(String toDo, String startDate, String completeDate, double price) throws ParseException {
		return taskStore.createTask(toDo, startDate, completeDate, price);
	}

	public List<Order> ordersDeleted(List<Order> orders) {
		return orderStore.ordersDeleted(orders);
	}

	public List<Order> ordersCompleted(List<Order> orders) {
		return orderStore.ordersCompleted(orders);
	}

	public List<Order> ordersCancelled(List<Order> orders) {
		return orderStore.ordersCancelled(orders);
	}



}
