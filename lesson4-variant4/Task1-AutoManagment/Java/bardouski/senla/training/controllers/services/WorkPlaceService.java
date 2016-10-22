package bardouski.senla.training.controllers.services;

import bardouski.senla.training.controllers.stores.WorkPlaceStore;

/**
 * addGarages(Garage... garages) addWorkPlaceInGivenGarage(String
 * WorkPlaceIdentifier, Garage garage) removeWorkPlace(String
 * WorkPlaceIdentifier) viewAllWorkPlaces() private String printPlace(WorkPlace
 * workPlace) viewAllFreeWorkPlaces()
 */
public class WorkPlaceService {

	private WorkPlaceStore workPlaceStore = new WorkPlaceStore();

	public void addWorkPlaceInGarage(int garageId) {

		workPlaceStore.addWorkPlace(garageId);
	}

	public void addGarage() {

		workPlaceStore.addGarage();
	}

	public void removeWorkPlaceInGarage(int placeId, int garageId) {

		workPlaceStore.removeWorkPlaceInGarage(placeId, garageId);

	}

}
