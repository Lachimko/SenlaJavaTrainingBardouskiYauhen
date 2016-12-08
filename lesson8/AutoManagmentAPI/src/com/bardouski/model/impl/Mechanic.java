package com.bardouski.model.impl;

import java.io.Serializable;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;

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

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public Order getCurrentOrder() {
		return currentOrder;
	}

	@Override
	public void setCurrentOrder(IOrder currentOrder) {
		this.currentOrder = (Order) currentOrder;
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
