package bardouski.senla.training.model;

import bardouski.senla.training.model.enums.OrderStatus;

public class Order {

	private static final String PATTERN_CLASSNAME = "Order/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_MECHANIC = "mechanic=";
	private static final String PATTERN_WORKPLACE = "workPlace=";
	private static final String PATTERN_TASK = "task=";
	private static final String PATTERN_STATUS = "orderStatus=";
	private static final String PATTERN_ENDFIELD = ";";

	private static int idCount = 1;
	private int id;
	private Mechanic mechanic;
	private WorkPlace workPlace;
	private Task task;
	private OrderStatus orderStatus = OrderStatus.INPROGRESS;

	// converter constructor without id increment
	public Order(int id, Mechanic mechanic, WorkPlace workPlace, Task task, OrderStatus orderStatus) {

		this.id = id;
		this.mechanic = mechanic;
		this.workPlace = workPlace;
		this.task = task;
		this.orderStatus = orderStatus;
	}

	public Order(Mechanic mechanic, WorkPlace workPlace, Task task) {
		this.id = idCount;
		idCount++;
		this.mechanic = mechanic;
		this.workPlace = workPlace;
		this.task = task;
	}

	public int getId() {
		return id;
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

	public static void setIdCount(int thisClassObjectCount) {
		idCount += thisClassObjectCount;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_ID).append(this.id).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_MECHANIC).append(PATTERN_ID);
		if (this.mechanic == null) {
			sb.append("null");
		} else {
			sb.append(this.mechanic.getId());
		}
		sb.append(PATTERN_ENDFIELD);

		sb.append(PATTERN_WORKPLACE).append(PATTERN_ID);
		if (this.workPlace == null) {
			sb.append("null");
		} else {
			sb.append(this.workPlace.getId());
		}
		sb.append(PATTERN_ENDFIELD);

		sb.append(PATTERN_TASK).append(PATTERN_ID);
		if (this.task == null) {
			sb.append("null");
		} else {
			sb.append(this.task);
		}
		sb.append(PATTERN_ENDFIELD);

		sb.append(PATTERN_STATUS).append(this.orderStatus).append(PATTERN_ENDFIELD);

		return sb.toString();

	}

}
