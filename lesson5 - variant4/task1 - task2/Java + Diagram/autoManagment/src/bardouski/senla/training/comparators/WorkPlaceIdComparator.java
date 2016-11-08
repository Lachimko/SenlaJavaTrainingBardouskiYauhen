package bardouski.senla.training.comparators;

import java.util.Comparator;

import bardouski.senla.training.model.WorkPlace;

public class WorkPlaceIdComparator implements Comparator<WorkPlace> {

	@Override
	public int compare(WorkPlace o1, WorkPlace o2) {
		return (o1.getId() > o2.getId()) ? 1 : -1;
	}

}
