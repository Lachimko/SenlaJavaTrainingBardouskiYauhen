package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.program.model.Garage;

public class GarageIdComparator implements Comparator<Garage> {

	@Override
	public int compare(Garage o1, Garage o2) {
		return (o1.getId() < o2.getId()) ? 1 : -1;
	}

}
