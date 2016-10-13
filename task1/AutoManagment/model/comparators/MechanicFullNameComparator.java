package model.comparators;

import java.util.Comparator;

import model.Mechanic;

public class MechanicFullNameComparator implements Comparator<Mechanic> {

	@Override
	public int compare(Mechanic o1, Mechanic o2) {
		return o1.getFullName().compareTo(o2.getFullName());
	}

}
