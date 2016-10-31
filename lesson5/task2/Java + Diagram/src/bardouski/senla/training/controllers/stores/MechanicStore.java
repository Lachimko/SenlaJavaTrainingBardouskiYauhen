package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bardouski.senla.training.comparators.MechanicFullNameComparator;
import bardouski.senla.training.controllers.stores.storeparent.Store;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Mechanic;

public class MechanicStore extends Store {

	private List<Mechanic> mechanics;

	public MechanicStore(DBProcessor dbProcessor) {

		if (Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}

		mechanics = dbProcessor.getMechanicsDB();
		setMechanicsLink(mechanics);
	}

	public void add(Mechanic mechanic) {
		mechanics.add(mechanic);
	}

	public void remove(Mechanic mechanic) {
		mechanics.remove(mechanic);
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


	
	public Mechanic getMechanic(int id) throws NoSuchObjectException{

		for (Mechanic mechanic : mechanics) {
			if (mechanic.getId() == id) {
				return mechanic;
			}
		}
		throw new NoSuchObjectException();
	}
	
	public List<Mechanic> getMechanics() {
		return this.mechanics;
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}

}
