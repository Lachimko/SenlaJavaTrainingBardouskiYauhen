package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.program.model.Order;


public class OrderCompleteDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {

		return (o1.getTask().getCompleteDate().before(o1.getTask().getCompleteDate())) ? -1 : 1;
	}

}
