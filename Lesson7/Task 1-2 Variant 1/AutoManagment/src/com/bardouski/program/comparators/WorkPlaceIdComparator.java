package com.bardouski.program.comparators;

import java.util.Comparator;

import com.bardouski.program.model.WorkPlace;

public class WorkPlaceIdComparator implements Comparator<WorkPlace> {

	@Override
	public int compare(WorkPlace o1, WorkPlace o2) {
		return (o1.getId() < o2.getId()) ? 1 : -1;
	}

}
