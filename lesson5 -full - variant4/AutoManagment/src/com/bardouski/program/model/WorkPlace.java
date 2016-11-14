package com.bardouski.program.model;

import java.io.Serializable;

public class WorkPlace implements Serializable{

	private static final long serialVersionUID = -2823107008398216104L;

	private static final String PATTERN_CLASSNAME = "WorkPlace/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_ORDER = "order=";
	private static final String PATTERN_NULL = "null";
	private static final String PATTERN_ENDFIELD = ";";

	private int id;
	private Order order;

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

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_ID).append(this.id).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_ORDER);

		if (this.order == null) {
			sb.append(PATTERN_NULL);
		} else {

			sb.append(this.order.getId());
		}
		sb.append(PATTERN_ENDFIELD);

		return sb.toString();
	}

}
