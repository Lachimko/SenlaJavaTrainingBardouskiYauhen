package com.bardouski.program.model;

import java.io.Serializable;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.program.model.enums.OrderStatus;

@PrintableObject(name = "Order")
public class Order implements Serializable, Cloneable {

	private static final long serialVersionUID = -6439747776065625336L;

	@Printable(name = "orderID", isDetailedOnly = false, order = 0)
	private int id;
	@PrintableRef(name = "orderMechanic", order = 1)
	private Mechanic mechanic;
	@PrintableRef(name = "orderWorkPlace", order = 2)
	private WorkPlace workPlace;
	@PrintableRef(name = "orderTask", order = 3)
	private Task task;
	@Printable(name = "orderStatus", isDetailedOnly = false, order = 4)
	private OrderStatus orderStatus = OrderStatus.INPROGRESS;

	public Order(Mechanic mechanic, WorkPlace workPlace, Task task) {
		this.mechanic = mechanic;
		this.workPlace = workPlace;
		this.task = task;
	}

	public Order() {
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

}
