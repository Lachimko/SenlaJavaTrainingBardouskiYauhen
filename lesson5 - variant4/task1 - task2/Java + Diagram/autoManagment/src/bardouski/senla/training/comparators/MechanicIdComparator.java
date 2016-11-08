package bardouski.senla.training.comparators;

import java.util.Comparator;

import bardouski.senla.training.model.Mechanic;

public class MechanicIdComparator implements Comparator<Mechanic>{

	@Override
	public int compare(Mechanic o1, Mechanic o2) {
		return (o1.getId() < o2.getId()) ? -1 : 1;
	}

}
