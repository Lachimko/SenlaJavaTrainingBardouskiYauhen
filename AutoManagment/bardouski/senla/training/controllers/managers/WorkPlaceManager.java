package bardouski.senla.training.controllers.managers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import bardouski.senla.training.controllers.converters.Converter;
import bardouski.senla.training.controllers.stores.PersonalStore;
import bardouski.senla.training.controllers.stores.WorkPlaceStore;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.StateOfGarages;
import bardouski.senla.training.model.WorkPlace;

/**
 * addGarages(Garage... garages) addWorkPlaceInGivenGarage(String
 * WorkPlaceIdentifier, Garage garage) removeWorkPlace(String
 * WorkPlaceIdentifier) viewAllWorkPlaces() private String printPlace(WorkPlace
 * workPlace) viewAllFreeWorkPlaces()
 */
public class WorkPlaceManager {

	private static WorkPlaceManager instance;
	private WorkPlaceStore workPlaceStore = new WorkPlaceStore();

	private StateOfGarages stateOfGarages;

	// Constructors, Getters/Setters

	// initialize stateOfGarages by new instance
	private WorkPlaceManager() {

		setStateOfGarages(Converter.returnStateOfGaragesFromDB(OrderManager.getDbDriver().getDBString()));

		if (getStateOfGarages() == null) {
			setStateOfGarages(workPlaceStore.createStateOfGarage());
		}
	}

	public static WorkPlaceManager getInstance() {

		return (instance == null) ? instance = new WorkPlaceManager() : instance;
	}

	public StateOfGarages getStateOfGarages() {
		return stateOfGarages;
	}

	public void setStateOfGarages(StateOfGarages stateOfGarages) {
		this.stateOfGarages = stateOfGarages;
	}

	// END Constructors, Getters/Setters

	// Set into stateOfGarages's ArrayList of garages values from remote db
	public void addGarage(String garageIdentifier) {
		this.setStateOfGarages(this.workPlaceStore.createGarage(garageIdentifier));
	}

	//TODO: ADD GARAGE TO STATE OF GARAGES
	//public void 	
	
	
	// public void addWorkPlaceInGivenGarage(String WorkPlaceIdentifier, Garage
	// garageObject) {
	//
	// for (Garage garage : stateOfGarages.getGarages()) {
	//
	// if (garage.equals(garageObject)) {
	//
	// garage.addWorkPlaces(new WorkPlace(WorkPlaceIdentifier));
	// }
	//
	// }
	// }

	public void removeWorkPlace(String WorkPlaceIdentifier) {

		for (Garage garage : stateOfGarages.getGarages()) {

			Iterator<WorkPlace> it = garage.getWorkPlaces().iterator();
			while (it.hasNext()) {

				if (it.next().getIdentifier().equals(WorkPlaceIdentifier)) {
					it.remove();
				}
			}
		}
	}

	public void viewAllWorkPlaces() {

		StringBuilder sb = new StringBuilder();
		sb.append("WORKPLACES:\n");

		for (Garage garage : stateOfGarages.getGarages()) {

			sb.append("Garage ID: ").append(garage.getIdentifier()).append("\n");

			for (WorkPlace workPlace : garage.getWorkPlaces()) {

				sb.append(printPlace(workPlace));
			}
		}

		System.out.println(sb);
	}

	private String printPlace(WorkPlace workPlace) {

		StringBuilder tmp_sb = new StringBuilder();

		tmp_sb.append("Workplace ID: ").append(workPlace.getIdentifier());
		tmp_sb.append("; Task: ").append(workPlace.getTask());
		tmp_sb.append("; Mechanic: ").append(workPlace.getMechanic());
		tmp_sb.append("\n");

		return tmp_sb.toString();
	}

	public void viewAllFreeWorkPlaces() {

		StringBuilder sb = new StringBuilder();
		sb.append("FREE WORKPLACES:\n");

		for (Garage garage : stateOfGarages.getGarages()) {

			sb.append("Garage ID: ").append(garage.getIdentifier()).append("\n");

			for (WorkPlace workPlace : garage.getWorkPlaces()) {

				if (workPlace.getTask() == null) {

					sb.append(printPlace(workPlace));
				}
			}
		}

		System.out.println(sb);
	}

	public Set<WorkPlace> returnFreePlaces() {

		StringBuilder sb = new StringBuilder();

		Set<WorkPlace> freeWorkPlacesSet = new HashSet<>();

		for (Garage garage : stateOfGarages.getGarages()) {

			for (WorkPlace workPlace : garage.getWorkPlaces()) {

				if (workPlace.getTask() == null) {
					freeWorkPlacesSet.add(workPlace);
				}
			}
		}
		return freeWorkPlacesSet;
	}

	public WorkPlace getFreePlaceFrom(Set<WorkPlace> places) {

		if (!places.isEmpty()) {
			Iterator<WorkPlace> it = places.iterator();
			return it.next();
		}

		final String MESSAGE = "No Free Places are aviable now";
		return null;
	}
}
