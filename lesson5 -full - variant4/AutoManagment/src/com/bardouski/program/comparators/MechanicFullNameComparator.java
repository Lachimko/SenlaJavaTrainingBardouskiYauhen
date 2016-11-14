package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.program.model.Mechanic;

public class MechanicFullNameComparator implements Comparator<Mechanic> {

	@Override
	public int compare(Mechanic o1, Mechanic o2) {
		return o1.getFullName().compareTo(o2.getFullName());
	}

}
