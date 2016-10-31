package bardouski.senla.training.controllers.services;

import bardouski.senla.training.controllers.stores.WorkPlaceStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.EmptyCollectionException;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.WorkPlace;

public class WorkPlaceService {

	private WorkPlaceStore workPlaceStore;
	
	public WorkPlaceService(DBProcessor dbProcessor) {
		workPlaceStore = new WorkPlaceStore(dbProcessor);
	}

	public void addWorkPlaceInGarage(Garage garage) {
		workPlaceStore.addWorkPlace(garage);
	}

	public void addGarage(Garage garage) {
		workPlaceStore.addGarage(garage);
	}

	public boolean removeWorkPlaceInGarage(int placeId, int garageId) throws EmptyCollectionException {
		return workPlaceStore.removeWorkPlaceInGarage(placeId, garageId);
	}
	
	public WorkPlace findFreePlace(){
		return workPlaceStore.findFreePlace();
	}

	public Garage getGarage(int id) throws NoSuchObjectException{
		return workPlaceStore.getGarage(id);
	}
}
