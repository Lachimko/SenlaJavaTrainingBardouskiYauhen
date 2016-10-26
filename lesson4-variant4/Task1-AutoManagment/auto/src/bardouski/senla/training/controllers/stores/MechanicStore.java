package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import bardouski.senla.training.comparators.MechanicFullNameComparator;
import bardouski.senla.training.controllers.stores.storeparent.Store;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class MechanicStore extends Store {

	private List<Mechanic> mechanics;

	/**
	 * Convert to String the new instance of Mechanic constructed with String
	 * mechanicFullName parameter; write converted instance to remote DB(add);
	 * Than we must return refreshed collection to PersonalManager's ArrayList
	 */

//	public MechanicStore(List<Mechanic> mechanics) {
//
//		this.mechanics = mechanics;
//	}

	public MechanicStore(DBProcessor dbProcessor) {
		
		if(Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}
		
		mechanics = dbProcessor.getMechanicsDB();
		setMechanicsLink(mechanics);
	}

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

	public Mechanic findFreeMechanic() {

		for (Mechanic mechanic : this.mechanics) {
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

	public List<Mechanic> getMechanics() {
		return this.mechanics;
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}

}
