package controllers;

import java.util.Iterator;

import model.Garage;
import model.Mechanic;
import model.StateOfGarages;
import model.WorkPlace;

/**
 * addGarages(Garage... garages)
 * addWorkPlaceInGivenGarage(String WorkPlaceIdentifier, Garage garage)
 * removeWorkPlace(String WorkPlaceIdentifier)
 * viewAllWorkPlaces()
 * private String printPlace(WorkPlace workPlace)
 * viewAllFreeWorkPlaces()
 */
public class WorkPlaceManager {

	private static WorkPlaceManager instance;

	private StateOfGarages stateOfGarages;

	private WorkPlaceManager() {
	}

	public static WorkPlaceManager getInstance() {
		if (instance == null) {
			return instance = new WorkPlaceManager();
		} else
			return instance;
	}

	public StateOfGarages getStateOfGarages() {
		return stateOfGarages;
	}

	public void setStateOfGarages(StateOfGarages stateOfGarages) {
		this.stateOfGarages = stateOfGarages;
	}

	public void addGarages(Garage... garages) {

		for (Garage garage : garages) {
			stateOfGarages.getGarages().add(garage);
		}
	}
	
	public void addWorkPlaceInGivenGarage(String WorkPlaceIdentifier, Garage garageObject) {

		for (Garage garage : stateOfGarages.getGarages()) {
			
			if (garage.equals(garageObject)){
			
				garage.addWorkPlaces(new WorkPlace(WorkPlaceIdentifier));
			}
			
		}
	}
	
	public void removeWorkPlace(String WorkPlaceIdentifier){

		for (Garage garage : stateOfGarages.getGarages()) {
			
			Iterator<WorkPlace> it = garage.getWorkPlaces().iterator();
			while (it.hasNext()){
				
				if (it.next().getIdentifier().equals(WorkPlaceIdentifier)){
					it.remove();
				}
			}
		}
	}

	public void viewAllWorkPlaces(){
		
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

	private String printPlace(WorkPlace workPlace){
		
		StringBuilder tmp_sb = new StringBuilder();
		
		tmp_sb.append("Workplace ID: ").append(workPlace.getIdentifier());
		tmp_sb.append("; Task: ").append(workPlace.getTask());
		tmp_sb.append("; Mechanic: ").append(workPlace.getMechanic());
		tmp_sb.append("\n");
		
		return tmp_sb.toString();
	}
	
	public void viewAllFreeWorkPlaces(){
		
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
}
