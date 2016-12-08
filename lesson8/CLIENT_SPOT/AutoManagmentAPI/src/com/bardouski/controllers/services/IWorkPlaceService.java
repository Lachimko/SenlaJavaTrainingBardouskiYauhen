package com.bardouski.controllers.services;

import java.util.List;

import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IWorkPlace;

public interface IWorkPlaceService {

	void addWorkPlaceInGarage(IGarage garage);

	void addGarage(IGarage garage);

	boolean removeWorkPlaceInGarage(IGarage garage, int placeId);
	
	IWorkPlace findFreePlace() throws EmptyCollectionException;

	IGarage getGarage(int id) throws NoSuchObjectException;
	
	List<? extends IGarage> getGarages();
	
}
