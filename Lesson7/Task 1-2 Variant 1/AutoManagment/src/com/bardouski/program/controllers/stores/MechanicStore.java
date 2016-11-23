package com.bardouski.program.controllers.stores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bardouski.program.comparators.MechanicFullNameComparator;
import com.bardouski.program.comparators.MechanicIdComparator;
import com.bardouski.program.exceptions.NoDBConnectionException;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Mechanic;

public class MechanicStore extends Store implements IMechanicStore {

	private int nextId;
	private List<Mechanic> mechanics;

	// Constructors, Getters/Setters
	public MechanicStore() throws NoDBConnectionException, FileNotFoundException, ClassNotFoundException {

		if (Store.dbProcessor != null) {
			mechanics = dbProcessor.getMechanicsDB();
			nextId = getIdCounterOfMechanicStore();
		}
	}

	/** Return correct id after processing mechanic list*/
	private int getIdCounterOfMechanicStore(){
		
		if (!mechanics.isEmpty()) {
			List<Mechanic> sortedList = new ArrayList<>(mechanics);
			Collections.sort(sortedList, new MechanicIdComparator());
			return (sortedList.get(0).getId()) + 1;
		} else {
			return 1;
		}
	}
		
	public List<Mechanic> getMechanics() {
		return this.mechanics;
	}

	// END Constructors, Getters/Setters

	public void add(Mechanic mechanic) {
		mechanic.setId(nextId);
		nextId++;
		mechanics.add(mechanic);
	}

	/**
	 * If no element was removed, exception will be thrown
	 * 
	 * @throws NoSuchObjectException
	 */
	public void remove(Mechanic mechanic) throws NoSuchObjectException {
		int size = mechanics.size();
		mechanics.remove(mechanic);

		if (size == mechanics.size()) {
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

	/** Return null if collection is empty */
	public List<Mechanic> sortMechanicsByFullName() {

		if (!mechanics.isEmpty()) {
			List<Mechanic> list = new ArrayList<>(mechanics);
			Collections.sort(list, new MechanicFullNameComparator());
			return list;
		}
		return null;
	}

	/** Return null if collection is empty */
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

	/**
	 * Return mechanic by Id.
	 * 
	 * @throws NoSuchObjectException
	 *             if no such object
	 */
	public Mechanic getMechanic(int id) throws NoSuchObjectException {

		for (Mechanic mechanic : mechanics) {
			if (mechanic.getId() == id) {
				return mechanic;
			}
		}
		throw new NoSuchObjectException();
	}

	/**
	 * Return Mechanic list, which was splited from CSV Strings. WARN: List is
	 * not returns from this method and set to any collection.
	 */
	public void importMechnaics() {

		List<Mechanic> imported = importFromCSV();

		for (Mechanic mechanic : imported) {

			for (Mechanic storeMechanic : mechanics) {
				if (storeMechanic.getId() == mechanic.getId()) {
					storeMechanic.setFullName(mechanic.getFullName());
					continue;
				}
			}
			mechanics.add(mechanic);
		}
	}

	/** Write String list to CSV */
	public void exportMechanics() {
		exportCSV(mechanics);
	}

}
