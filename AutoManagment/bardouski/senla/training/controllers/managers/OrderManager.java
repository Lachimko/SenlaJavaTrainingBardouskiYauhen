package bardouski.senla.training.controllers.managers;

import java.util.ArrayList;
import java.util.List;

import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;
import bardouski.senla.training.model.WorkPlace;
import bardouski.senla.training.model.db.DBDriver;

/**
 * viewOrders() String returnStringOfOrder(Order order)
 * 
 *
 */
public class OrderManager {

	public static final String DB_PATH = System.getProperty("user.dir") + "/temp.txt";

	private static OrderManager instance;
	
	private static DBDriver dbDriver;

	private TaskManager taskManager;
	private PersonalManager personalManager;
	private WorkPlaceManager workPlaceManager;

	private List<Order> orders = new ArrayList<>();

	/** Initializing all managers and DB Driver with db path */
	private OrderManager() {

		dbDriver = new DBDriver();
		dbDriver.init(DB_PATH);

		this.personalManager = PersonalManager.getInstance();
		this.workPlaceManager = WorkPlaceManager.getInstance();
		this.taskManager = TaskManager.getInstance();
	}

	public static OrderManager init() {

		if (instance == null) {
			return instance = new OrderManager();
		} else
			return instance;
	}

	public void viewOrders() {

		StringBuilder sb = new StringBuilder();

		sb.append(viewOrders(this.orders));
		System.out.println(sb);
	};

	public String viewOrders(List<Order> filteredOrders) {

		StringBuilder sb = new StringBuilder();

		for (Order order : filteredOrders) {
			sb.append(returnStringOfOrder(order));
		}

		return sb.toString();
	}

	public static DBDriver getDbDriver() {
		return dbDriver;
	}
	// public Set<Order> viewCompleteOrders(){
	//
	// Set<Order> filteredSet = new HashSet<>();
	//
	// for (Order order : this.orders) {
	// if (order.getOrderStatus().equals(OrderStatus.Ready)) {
	//
	// filteredSet.add(order);
	// }
	// }
	// return filteredSet;
	// }

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

	private void addAndSync(Order order) {

		add(order);
		// remove task from TaskManager
		taskManager.setCurrentTask(null);
		// other link
		order.getMechanic().setCurrentOrder(order);
		order.getWorkPlace().setTask(order.getTask());
		order.getWorkPlace().setMechanic(order.getMechanic());
	}

	private void add(Order order) {
		this.orders.add(order);
	}

	// viewCompleteOrders();
	// viewDeletedOrders();
	// viewCancelledOrders();

	///// GET / SET
	public TaskManager getTaskManager() {
		return taskManager;
	}

//	public void setTaskManager(TaskManager taskManager) {
//		this.taskManager = taskManager;
//	}

	public PersonalManager getPersonalManager() {
		return personalManager;
	}

//	public void setPersonalManager(PersonalManager personalManager) {
//		this.personalManager = personalManager;
//	}

	public WorkPlaceManager getWorkPlaceManager() {
		return workPlaceManager;
	}

//	public void setWorkPlaceManager(WorkPlaceManager workPlaceManager) {
//		this.workPlaceManager = workPlaceManager;
//	}

}
