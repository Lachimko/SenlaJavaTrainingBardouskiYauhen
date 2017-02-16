package com.bardouski.model.impl;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

@Entity
public class Ticket implements IOrder, Cloneable {

	private static final long serialVersionUID = -6439747776065625336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	private Mechanic mechanic;

	@OneToOne(fetch = FetchType.LAZY)
	private WorkPlace workPlace;

	@OneToOne
	private Task task;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.INPROGRESS;

	public Ticket() {
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
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
