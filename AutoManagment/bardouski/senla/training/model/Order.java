package bardouski.senla.training.model;

import bardouski.senla.training.model.enums.OrderStatus;

public class Order {

	private int id;
	private Mechanic mechanic;
	private WorkPlace workPlace;
	private Task task;
	private OrderStatus orderStatus = OrderStatus.INPROGRESS;
	
	public Order(Mechanic mechanic, WorkPlace workPlace, Task task) {
		this.mechanic = mechanic;
		this.workPlace = workPlace;
		this.task = task;
	}

	public Order(Task task) {
		this.task = task;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public WorkPlace getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(WorkPlace workPlace) {
		this.workPlace = workPlace;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
