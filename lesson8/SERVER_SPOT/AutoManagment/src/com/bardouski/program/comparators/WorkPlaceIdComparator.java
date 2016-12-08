package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.model.IWorkPlace;

public class WorkPlaceIdComparator implements Comparator<IWorkPlace> {

	@Override
	public int compare(IWorkPlace o1, IWorkPlace o2) {
		return (o1.getId() < o2.getId()) ? 1 : -1;
	}

}
