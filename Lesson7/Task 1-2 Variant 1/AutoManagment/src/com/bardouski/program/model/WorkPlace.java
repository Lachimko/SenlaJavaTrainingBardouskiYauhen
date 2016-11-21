package com.bardouski.program.model;

import java.io.Serializable;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;

@PrintableObject(name = "WorkPlace")
public class WorkPlace implements Serializable{

	private static final long serialVersionUID = -2823107008398216104L;

	@Printable(name = "workPlaceID", isDetailedOnly = false, order = 0)
	private int id;
	@PrintableRef(name = "workPlaceOrder", order = 1)
	private Order order;

	public WorkPlace(){
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
