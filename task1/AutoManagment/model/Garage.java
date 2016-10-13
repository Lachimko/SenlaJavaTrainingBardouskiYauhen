package model;

import java.util.HashSet;
import java.util.Set;

public class Garage{

	private String identifier;
	private Set<WorkPlace> workPlaces = new HashSet<>();
	
	public Garage(String identifier) {
		this.identifier = identifier;
	}

	//addWorkPlace
	//viewWorkPlace
	//viewAllWorkPlaces
	
	public Set<WorkPlace> getWorkPlaces() {
		return workPlaces;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void addWorkPlaces(WorkPlace... workPlaces) {

		for (WorkPlace workPlace : workPlaces) {
			this.workPlaces.add(workPlace);
		}
	
	}
	
	
	
}
