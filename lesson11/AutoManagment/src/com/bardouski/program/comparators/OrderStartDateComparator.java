package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.model.IOrder;

@Deprecated
public class OrderStartDateComparator implements Comparator<IOrder> {

	@Override
	public int compare(IOrder o1, IOrder o2) {
		return (o1.getTask().getStartDate().before(o1.getTask().getStartDate())) ? -1 : 1;
	}

}
