package controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import model.Mechanic;
import model.Order;
import model.Task;
import model.WorkPlace;
import model.enums.OrderStatus;

/**
 * viewOrders() String returnStringOfOrder(Order order)
 * 
 *
 */
public class OrderManager {

	private static OrderManager instance;

	private TaskManager taskManager;
	private PersonalManager personalManager;
	private WorkPlaceManager workPlaceManager;

	private Set<Order> orders = new HashSet<>();

	private OrderManager(PersonalManager personalManager, WorkPlaceManager workPlaceManager, TaskManager taskManager) {

		this.personalManager = personalManager;
		this.workPlaceManager = workPlaceManager;
		this.taskManager = taskManager;
	}

	public static OrderManager init(PersonalManager personalManager, WorkPlaceManager workPlaceManager,
			TaskManager taskManager) {

		if (instance == null) {
			return instance = new OrderManager(personalManager, workPlaceManager, taskManager);
		} else
			return instance;
	}

	public void viewOrders() {

		StringBuilder sb = new StringBuilder();
		sb.append("ORDERS:\n");

		sb.append(viewOrders(this.orders));
		System.out.println(sb);
	};

	public String viewOrders(Set<Order> filteredOrders){
		
		StringBuilder sb = new StringBuilder();
		
		for (Order order : filteredOrders) {
			sb.append(returnStringOfOrder(order));
		}
		
		return sb.toString();
	}

//	public Set<Order> viewCompleteOrders(){
//	
//		Set<Order> filteredSet = new HashSet<>();
//		
//		for (Order order : this.orders) {
//			if (order.getOrderStatus().equals(OrderStatus.Ready)) {
//				
//				filteredSet.add(order);
//			}
//		}
//		return filteredSet;
//	}
	
	private String returnStringOfOrder(Order order) {

		StringBuilder sb = new StringBuilder();

		sb.append("Order Task: ").append("TODO: ").append(order.getTask().getToDo()).append("\n");
		sb.append("Request Date: ").append(order.getTask().getRequestDate()).append("; ");
		sb.append("Start Date: ").append(order.getTask().getStartDate()).append("; ");
		sb.append("Complete Date: ").append(order.getTask().getCompleteDate()).append("; ");
		sb.append("Price: ").append(order.getTask().getPrice()).append("\n");
		sb.append("Mechanic: ").append(order.getMechanic().getFullName()).append("; ");
		sb.append("Workplace: ").append(order.getWorkPlace().getIdentifier()).append("; ");
		sb.append("OrderStatus: ").append(order.getOrderStatus()).append("; ");
		sb.append("\n");

		return sb.toString();
	}

	public void createOrder(String toDo, String requestDate, String startDate, String completeDate, double price) {

		final String FAIL_MESSAGE = "No orders were created. Can't find free Mechanic of Place.\nTask is saved, try later...";
		Task currentTask = this.taskManager.createTask(toDo, startDate, completeDate, price);

		Mechanic currentOrderMechanic = personalManager.getFreeMechanicFrom(personalManager.returnFreeMechanics());
		WorkPlace currentworkPlace = workPlaceManager.getFreePlaceFrom(workPlaceManager.returnFreePlaces());

		Order currentOrder = (currentOrderMechanic != null && currentworkPlace != null)
				? new Order(currentOrderMechanic, currentworkPlace, currentTask) : null;

		if (currentOrder == null) {
			
			System.out.println(FAIL_MESSAGE);
		} else {
			
			addAndSync(currentOrder);
		}
	}
	
	private void addAndSync(Order order){
		
		add(order);
		//remove task from TaskManager
		taskManager.setCurrentTask(null);
		// other link
		order.getMechanic().setCurrentOrder(order);;
		order.getWorkPlace().setTask(order.getTask());
		order.getWorkPlace().setMechanic(order.getMechanic());
	}
	
	private void add(Order order){
		this.orders.add(order);
	}

	// viewCompleteOrders();
	// viewDeletedOrders();
	// viewCancelledOrders();

}
