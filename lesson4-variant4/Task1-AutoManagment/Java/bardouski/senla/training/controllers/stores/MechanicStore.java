package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import bardouski.senla.training.comparators.MechanicFullNameComparator;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class MechanicStore {

	private static List<Mechanic> mechanics = new ArrayList<Mechanic>();

	/**
	 * Convert to String the new instance of Mechanic constructed with String
	 * mechanicFullName parameter; write converted instance to remote DB(add);
	 * Than we must return refreshed collection to PersonalManager's ArrayList
	 */

	public void add(String mechanicFullName) {

		mechanics.add(new Mechanic(mechanicFullName));

	}

	public void remove(int id) {

		Iterator<Mechanic> it = mechanics.iterator();

		while (it.hasNext()) {

			if (it.next().getId() == id) {
				it.remove();
			}
		}
	}

	public static Mechanic findFreeMechanic() {

		for (Mechanic mechanic : mechanics) {
			if (mechanic.getCurrentOrder() == null) {
				return mechanic;
			}
		}
		return null;
	}

	public List<Mechanic> sortMechanicsByFullName() {

		List<Mechanic> list = new ArrayList<>(mechanics);

		Collections.sort(list, new MechanicFullNameComparator());

		return list;
	}

	public List<Mechanic> sortMechanicsByCurrentWork() {

		List<Mechanic> list = new ArrayList<>(mechanics);

		for (Mechanic mechanic : mechanics) {
			if (mechanic.getCurrentOrder() != null) {
				list.add(0, mechanic);
			}
		}

		return list;
	}

	public Order returnOrderOfMechanic(int mechanicId) {

		for (Mechanic mechanic : mechanics) {
			if (mechanic.getId() == mechanicId) {
				return mechanic.getCurrentOrder();
			}
		}
		return null;
	}

	public static List<Mechanic> getMechanics() {
		return mechanics;
	}

	public static void setMechanics(List<Mechanic> mechanics) {
		MechanicStore.mechanics = mechanics;
	}

}
