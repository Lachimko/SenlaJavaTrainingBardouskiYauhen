package com.bardouski.program.model;

import java.io.Serializable;

public class Mechanic implements Serializable{

	private static final long serialVersionUID = -1256372256008341555L;
	
	private static final String PATTERN_CLASSNAME = "Mechanic/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_FULLNAME = "fullName=";
	private static final String PATTERN_ORDER = "currentOrder=";
	private static final String PATTERN_ENDFIELD = ";";

	private int id;
	private String fullName;
	private Order currentOrder;

	public Mechanic(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_ID).append(this.id).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_FULLNAME).append(this.fullName).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_ORDER);

		if (this.currentOrder == null) {
			sb.append("null");
		} else {
			sb.append(this.currentOrder.getId());
		}
		sb.append(PATTERN_ENDFIELD);

		return sb.toString();
	}

}
