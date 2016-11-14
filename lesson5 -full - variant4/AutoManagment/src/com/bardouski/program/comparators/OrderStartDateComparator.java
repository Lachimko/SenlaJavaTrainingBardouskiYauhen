package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.program.model.Order;

public class OrderStartDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {

		return (o1.getTask().getStartDate().before(o1.getTask().getStartDate())) ? -1 : 1;
	}

}
