package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.model.Garage;
import com.bardouski.program.model.WorkPlace;

public interface IWorkPlaceService {

	void addWorkPlaceInGarage(Garage garage);

	void addGarage(Garage garage);

	boolean removeWorkPlaceInGarage(Garage garage, int placeId);
	
	WorkPlace findFreePlace() throws Exception;

	Garage getGarage(int id) throws Exception;
	
	List<Garage> getGarages();
	
}
