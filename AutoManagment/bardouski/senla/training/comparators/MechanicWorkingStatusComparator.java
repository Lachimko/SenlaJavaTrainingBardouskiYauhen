package bardouski.senla.training.comparators;

import java.util.Comparator;

import bardouski.senla.training.model.Mechanic;

public class MechanicWorkingStatusComparator implements Comparator<Mechanic> {

	@Override
	public int compare(Mechanic o1, Mechanic o2) {
		
		int obj1 = o1.getCurrentOrder() == null ? 0 : 1;
		int obj2 = o2.getCurrentOrder() == null ? 0 : 1;
		
		return obj1 - obj2;
	}

}
