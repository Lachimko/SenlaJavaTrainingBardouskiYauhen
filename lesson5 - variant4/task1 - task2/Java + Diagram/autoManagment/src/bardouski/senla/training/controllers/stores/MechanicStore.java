package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bardouski.senla.training.comparators.MechanicFullNameComparator;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Mechanic;

public class MechanicStore extends Store {

	private List<Mechanic> mechanics;

	// Constructors, Getters/Setters
	public MechanicStore(DBProcessor dbProcessor) {

		if (super.dbProcessor == null) {
			super.dbProcessor = dbProcessor;
		}

		mechanics = dbProcessor.getMechanicsDB();
	}

	public List<Mechanic> getMechanics() {
		return this.mechanics;
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}
	// END Constructors, Getters/Setters

	public void add(Mechanic mechanic) {
		mechanics.add(mechanic);
	}

	/** If no element was removed, exception will be thrown
	 * @throws NoSuchObjectException
	 */
	public void remove(Mechanic mechanic) throws NoSuchObjectException {
		int size = mechanics.size();
		mechanics.remove(mechanic);
		
		if (size == mechanics.size()){
			throw new NoSuchObjectException();
		}
	}

	/**
	 * Return first mechanic with null currentOrder, return null if there is no
	 * such object
	 */
	public Mechanic findFreeMechanic() {

		for (Mechanic mechanic : this.mechanics) {
			if (mechanic.getCurrentOrder() == null) {
				return mechanic;
			}
		}
		return null;
	}

	/**Return null if collection is empty*/
	public List<Mechanic> sortMechanicsByFullName() {

		if (!mechanics.isEmpty()) {
			List<Mechanic> list = new ArrayList<>(mechanics);
			Collections.sort(list, new MechanicFullNameComparator());
			return list;
		}
		return null;
	}

	/**Return null if collection is empty*/
	public List<Mechanic> sortMechanicsByCurrentWork() {

		if (!mechanics.isEmpty()) {
			List<Mechanic> list = new ArrayList<>();

			for (Mechanic mechanic : mechanics) {
				if (mechanic.getCurrentOrder() != null) {
					list.add(0, mechanic);
				} else {
					list.add(mechanic);
				}
			}
			return list;
		}
		return null;
	}

	/**Return mechanic by Id.
	 * @throws NoSuchObjectException if no such object */
	public Mechanic getMechanic(int id) throws NoSuchObjectException {

		for (Mechanic mechanic : mechanics) {
			if (mechanic.getId() == id) {
				return mechanic;
			}
		}
		throw new NoSuchObjectException();
	}

}
