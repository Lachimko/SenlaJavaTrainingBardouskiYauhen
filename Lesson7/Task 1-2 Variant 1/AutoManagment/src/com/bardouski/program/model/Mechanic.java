package com.bardouski.program.model;

import java.io.Serializable;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;

@PrintableObject(name = "Mechanic")
public class Mechanic implements Serializable, IMechanic {

	private static final long serialVersionUID = -1256372256008341555L;

	@Printable(name = "mechanicID", isDetailedOnly = false, order = 0)
	private int id;
	@Printable(name = "mechanicFullName", isDetailedOnly = false, order = 1)
	private String fullName;
	@PrintableRef(name = "mechanicOrder", order = 2)
	private Order currentOrder;

	public Mechanic(String fullName) {
		this.fullName = fullName;
	}

	public Mechanic() {
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public IOrder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(IOrder currentOrder) {
		this.currentOrder = (Order) currentOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
