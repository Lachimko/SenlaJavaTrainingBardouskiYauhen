package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.model.IGarage;

public class GarageIdComparator implements Comparator<IGarage> {

	@Override
	public int compare(IGarage o1, IGarage o2) {
		return (o1.getId() < o2.getId()) ? 1 : -1;
	}

}
