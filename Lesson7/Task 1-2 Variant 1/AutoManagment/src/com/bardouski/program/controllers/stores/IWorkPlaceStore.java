package com.bardouski.program.controllers.stores;

import java.util.List;

import com.bardouski.program.exceptions.EmptyCollectionException;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.WorkPlace;

public interface IWorkPlaceStore {

	List<Garage> getGarages();

	void addWorkPlace(Garage garage);

	void addGarage(Garage garage);

	/**
	 * Find workplace by ID in garage.return false if workplace not found
	 */
	boolean removeWorkPlaceInGarage(Garage garage, int placeId);

	/**
	 * Return first workplace with null Order, if no such object return null
	 * 
	 * @throws EmptyCollectionException
	 */
	WorkPlace getFreePlace() throws EmptyCollectionException;

	/** return garage by id. Exception if no such garage */
	Garage getGarage(int id) throws NoSuchObjectException;

}
