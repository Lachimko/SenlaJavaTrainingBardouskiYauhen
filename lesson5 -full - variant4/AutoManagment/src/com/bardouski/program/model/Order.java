package com.bardouski.program.model;

import java.io.Serializable;

import com.bardouski.program.model.enums.OrderStatus;

public class Order implements Serializable, Cloneable{

	private static final long serialVersionUID = -6439747776065625336L;

	private static final String PATTERN_CLASSNAME = "Order/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_MECHANIC = "mechanic=";
	private static final String PATTERN_WORKPLACE = "workPlace=";
	private static final String PATTERN_TASK = "task=";
	private static final String PATTERN_STATUS = "orderStatus=";
	private static final String PATTERN_ENDFIELD = ";";

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
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

		sb.append(PATTERN_TASK);
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
