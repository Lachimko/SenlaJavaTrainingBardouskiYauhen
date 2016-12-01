package com.bardouski.program.model;

import java.io.Serializable;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;

@PrintableObject(name = "WorkPlace")
public class WorkPlace implements IWorkPlace, Serializable {

	private static final long serialVersionUID = -2823107008398216104L;

	@Printable(name = "workPlaceID", isDetailedOnly = false, order = 0)
	private int id;
	@PrintableRef(name = "workPlaceOrder", order = 1)
	private Order order;

	public WorkPlace() {
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public void setOrder(IOrder order) {
		this.order = (Order) order;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
