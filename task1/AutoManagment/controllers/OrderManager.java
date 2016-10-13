package controllers;

import java.util.HashSet;
import java.util.Set;

import model.Garage;
import model.Mechanic;
import model.Order;
import model.Task;
import model.WorkPlace;

public class OrderManager {

	private static OrderManager instance;
	private TaskManager taskManager;

	private Set<Order> orders = new HashSet<>();

	private OrderManager() {
	}

	public OrderManager init(TaskManager taskManager) {

		this.taskManager = taskManager;

		if (instance == null) {
			return instance = new OrderManager();
		} else
			return instance;
	}

	public void viewOrders(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("ORDERS:\n");
		
		for (Order order : orders) {
			
			sb.append("Order Task: ").append("TODO: ").append(order.getTask().getToDo()).append("\n");
			sb.append("Request Date: ").append(order.getTask().getRequestDate());
			sb.append("Start Date: ").append(order.getTask().getStartDate());
			sb.append("Complete Date: ").append(order.getTask().getCompleteDate());
			sb.append("Price: ").append(order.getTask().getPrice()).append("\n");
			sb.append("Mechanic: ").append(order.getMechanic().getFullName());
			sb.append("Workplace: ").append(order.getWorkPlace().getIdentifier());
			sb.append("OrderStatus: ").append(order.getOrderStatus());
			sb.append("\n");
		}
		
		System.out.println(sb);
	};	

/*	void createOrder(String toDo, String requestDate, String startDate, String completeDate, double price){
		
		Task currentTask = this.taskManager.createTask(toDo, startDate, completeDate, price);
		
		//Mechanic currentOrderMechanic = 

		Order currentOrder = new Order(currentTask);
	}
*/	
	// taskManager.
	// };
	//

	// viewCompleteOrders();
	// viewDeletedOrders();
	// viewCancelledOrders();

}
