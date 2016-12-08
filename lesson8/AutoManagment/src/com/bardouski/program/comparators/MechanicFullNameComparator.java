package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.model.IMechanic;

public class MechanicFullNameComparator implements Comparator<IMechanic> {

	@Override
	public int compare(IMechanic o1, IMechanic o2) {
		return o1.getFullName().compareTo(o2.getFullName());
	}

}
