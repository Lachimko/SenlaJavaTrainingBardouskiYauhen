package com.bardouski.controllers.services;

import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IWorkPlace;

public interface IWorkPlaceService {

	void createWorkPlaceInGarage(int garageID);

	void addGarage();

	boolean removeWorkPlaceInGarage(int garageID, int placeID) throws NoSuchObjectException;
	
	IWorkPlace findFreePlace() throws NoSuchObjectException;

	IGarage getGarage(int id) throws NoSuchObjectException;
	
	List<? extends IGarage> getGarages();
	
}
