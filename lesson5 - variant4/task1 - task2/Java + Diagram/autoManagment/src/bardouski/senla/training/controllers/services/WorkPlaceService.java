package bardouski.senla.training.controllers.services;

import java.util.List;

import bardouski.senla.training.controllers.stores.WorkPlaceStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.EmptyCollectionException;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.WorkPlace;

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
