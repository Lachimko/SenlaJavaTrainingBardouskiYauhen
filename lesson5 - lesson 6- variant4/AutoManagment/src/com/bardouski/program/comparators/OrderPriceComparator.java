package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.program.model.Order;


public class OrderPriceComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {

		double o1price = o1.getTask().getPrice();
		double o2price = o2.getTask().getPrice();

		if (o1price == o2price) {
			return 0;
		} else {
			return (o1price > o2price) ? 1 : -1;
		}
	}

}
