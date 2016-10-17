package bardouski.senla.training.model;

import java.util.ArrayList;
import java.util.List;

public class Garage {

	private static int idCount = 1;

	private int id;
	private String identifier;
	private List<WorkPlace> workPlaces = new ArrayList<WorkPlace>();

	// Constructors, Getters/Setters

	// converter constructor without id increment
	public Garage(int id, String identifier) {
		this.id = id;
		this.identifier = identifier;
	}

	// converter constructor without id increment
	public Garage(int id, String identifier, List<WorkPlace> workPlaces) {
		this.id = id;
		this.identifier = identifier;
		this.workPlaces = workPlaces;
	}

	public Garage(String identifier) {
		this.id = idCount;
		idCount++;
		this.identifier = identifier;
	}

	public int getId() {
		return id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public List<WorkPlace> getWorkPlaces() {
		return workPlaces;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Garage{");
		sb.append("Id: ").append(this.getId()).append(", Identifier: ").append(this.getIdentifier())
				.append(", WorkPlaces: ").append(this.getWorkPlaces()).append("}");
		return sb.toString();
	}

	// END Constructors, Getters/Setters

	// addWorkPlace
	// viewWorkPlace
	// viewAllWorkPlaces

	// public void addWorkPlaces(WorkPlace... workPlaces) {
	//
	// for (WorkPlace workPlace : workPlaces) {
	// this.workPlaces.add(workPlace);
	// }
	//
	// }

}
