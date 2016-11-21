package com.bardouski.program.controllers.stores;

import java.util.List;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Mechanic;

public interface IMechanicStore {

	List<Mechanic> getMechanics();

	// END Constructors, Getters/Setters

	void add(Mechanic mechanic);

	/**
	 * If no element was removed, exception will be thrown
	 * 
	 * @throws NoSuchObjectException
	 */
	void remove(Mechanic mechanic) throws NoSuchObjectException;

	/**
	 * Return first mechanic with null currentOrder, return null if there is no
	 * such object
	 */
	Mechanic findFreeMechanic();

	/** Return null if collection is empty */
	List<Mechanic> sortMechanicsByFullName();

	/** Return null if collection is empty */
	List<Mechanic> sortMechanicsByCurrentWork();

	/**
	 * Return mechanic by Id.
	 * 
	 * @throws NoSuchObjectException
	 *             if no such object
	 */
	Mechanic getMechanic(int id) throws NoSuchObjectException;

	/**
	 * Return Mechanic list, which was splited from CSV Strings. WARN: List is
	 * not returns from this method and set to any collection.
	 */
	void importMechnaics();

	/** Write String list to CSV */
	void exportMechanics();

}
