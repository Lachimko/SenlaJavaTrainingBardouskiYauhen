package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.controllers.stores.WorkPlaceStore;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.exceptions.EmptyCollectionException;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.WorkPlace;

public class WorkPlaceService {

	private WorkPlaceStore workPlaceStore;
	
	//Constructors, Getters/Setters
	public WorkPlaceService(DBProcessor dbProcessor) {
		workPlaceStore = new WorkPlaceStore(dbProcessor);
	}

	//END Constructors, Getters/Setters
	
	public void addWorkPlaceInGarage(Garage garage) {
		workPlaceStore.addWorkPlace(garage);
	}

	public void addGarage(Garage garage) {
		workPlaceStore.addGarage(garage);
	}

	public boolean removeWorkPlaceInGarage(Garage garage, int placeId) {
		return workPlaceStore.removeWorkPlaceInGarage(garage, placeId);
	}
	
	public WorkPlace findFreePlace() throws EmptyCollectionException {
		return workPlaceStore.getFreePlace();
	}

	public Garage getGarage(int id) throws NoSuchObjectException{
		return workPlaceStore.getGarage(id);
	}
	
	public List<Garage> getGarages(){
		return workPlaceStore.getGarages();
	}
}
