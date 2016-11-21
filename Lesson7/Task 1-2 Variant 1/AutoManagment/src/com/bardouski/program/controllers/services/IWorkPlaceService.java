package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.exceptions.EmptyCollectionException;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.WorkPlace;

public interface IWorkPlaceService {

	void addWorkPlaceInGarage(Garage garage);

	void addGarage(Garage garage);

	boolean removeWorkPlaceInGarage(Garage garage, int placeId);
	
	WorkPlace findFreePlace() throws EmptyCollectionException;

	Garage getGarage(int id) throws NoSuchObjectException;
	
	List<Garage> getGarages();
	
}
