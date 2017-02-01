package com.bardouski.controllers.stores;

import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IMechanic;

@Deprecated
public interface IMechanicStore extends IStore {

	List<? extends IMechanic> getMechanics();

	/** Add new mechanic to MechanicStore collection */
	void add(IMechanic mechanic);
	
	/** Add new mechanic with @param fullName to MechanicStore collection */
	void add(String fullName);

	/**
	 * If no element was removed, exception will be thrown
	 * 
	 * @throws NoSuchObjectException
	 */
	void remove(IMechanic mechanic) throws NoSuchObjectException;

	/**
	 * Return first mechanic with null currentOrder, return null if there is no
	 * such object
	 */
	IMechanic findFreeMechanic();

	/** Return null if collection is empty */
	List<? extends IMechanic> sortMechanicsByFullName();

	/** Return null if collection is empty */
	List<? extends IMechanic> sortMechanicsByCurrentWork();

	/**
	 * Return mechanic by Id.
	 * 
	 * @throws NoSuchObjectException
	 *             if no such object
	 */
	IMechanic getMechanic(int id) throws NoSuchObjectException;

	/**
	 * Return Mechanic list, which was splited from CSV Strings. WARN: List is
	 * not returns from this method and set to any collection.
	 */
	void importMechnaics();

	/** Write String list to CSV */
	void exportMechanics();

}
