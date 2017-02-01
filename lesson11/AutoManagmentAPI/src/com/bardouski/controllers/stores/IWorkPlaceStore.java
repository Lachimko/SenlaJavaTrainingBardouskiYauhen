package com.bardouski.controllers.stores;

import java.util.List;

import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IWorkPlace;

@Deprecated
public interface IWorkPlaceStore extends IStore{

	List<? extends IGarage> getGarages();

	void addWorkPlace(IGarage garage);

	void addGarage(IGarage garage);

	/**
	 * Find workplace by ID in garage.return false if workplace not found
	 */
	boolean removeWorkPlaceInGarage(IGarage garage, int placeId);

	/**
	 * Return first workplace with null Order, if no such object return null
	 * 
	 * @throws EmptyCollectionException
	 */
	IWorkPlace getFreePlace() throws EmptyCollectionException;

	/** return garage by id. Exception if no such garage */
	IGarage getGarage(int id) throws NoSuchObjectException;

}
