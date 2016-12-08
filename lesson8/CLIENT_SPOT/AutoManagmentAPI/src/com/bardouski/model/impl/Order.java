package com.bardouski.model.impl;

import java.io.Serializable;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

@PrintableObject(name = "Order")
public class Order implements Serializable, Cloneable, IOrder {

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

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	@Override
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public Mechanic getMechanic() {
		return mechanic;
	}

	@Override
	public void setMechanic(IMechanic mechanic) {
		this.mechanic = (Mechanic) mechanic;
	}

	@Override
	public WorkPlace getWorkPlace() {
		return workPlace;
	}

	@Override
	public void setWorkPlace(IWorkPlace workPlace) {
		this.workPlace = (WorkPlace) workPlace;
	}

	@Override
	public Task getTask() {
		return task;
	}

	@Override
	public void setTask(ITask task) {
		this.task = (Task) task;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
