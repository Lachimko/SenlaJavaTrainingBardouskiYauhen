package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.model.IMechanic;

public class MechanicIdComparator implements Comparator<IMechanic>{

	@Override
	public int compare(IMechanic o1, IMechanic o2) {
		return (o1.getId() < o2.getId()) ? 1 : -1;
	}

}
