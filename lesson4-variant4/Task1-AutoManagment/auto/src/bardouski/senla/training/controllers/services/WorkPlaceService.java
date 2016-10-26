package bardouski.senla.training.controllers.services;

import bardouski.senla.training.controllers.stores.WorkPlaceStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.model.WorkPlace;

/**
 * addGarages(Garage... garages) addWorkPlaceInGivenGarage(String
 * WorkPlaceIdentifier, Garage garage) removeWorkPlace(String
 * WorkPlaceIdentifier) viewAllWorkPlaces() private String printPlace(WorkPlace
 * workPlace) viewAllFreeWorkPlaces()
 */
public class WorkPlaceService {

	private WorkPlaceStore workPlaceStore;
	
	public WorkPlaceService(DBProcessor dbProcessor) {
		workPlaceStore = new WorkPlaceStore(dbProcessor);
	}
	
//	public WorkPlaceService(List<Garage> listFromDB) {
//		this.workPlaceStore = new WorkPlaceStore(listFromDB);
//	}

	public void addWorkPlaceInGarage(int garageId) {
		workPlaceStore.addWorkPlace(garageId);
	}

	public void addGarage() {
		workPlaceStore.addGarage();
	}

	public void removeWorkPlaceInGarage(int placeId, int garageId) {
		workPlaceStore.removeWorkPlaceInGarage(placeId, garageId);
	}
	
	public WorkPlace findFreePlace(){
		return workPlaceStore.findFreePlace();
	}

}
