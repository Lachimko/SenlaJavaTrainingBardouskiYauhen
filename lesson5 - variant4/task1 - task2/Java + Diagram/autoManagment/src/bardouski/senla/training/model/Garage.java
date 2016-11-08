package bardouski.senla.training.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Garage implements Serializable{

	private static final String PATTERN_CLASSNAME = "Garage/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_WORKPLACES = "workPlaces=";
	private static final String PATTERN_ENDFIELD = ";";

	private static int idCount = 1;

	private int id;
	private List<WorkPlace> workPlaces = new ArrayList<WorkPlace>();

	// Constructors, Getters/Setters

	// converter constructor without id increment
	public Garage(int id) {
		this.id = id;
		idCount++;
	}

	// converter constructor without id increment
	public Garage(int id, List<WorkPlace> workPlaces) {
		this.id = id;
		this.workPlaces = workPlaces;
		idCount++;
	}

	public Garage() {
		this.id = idCount;
		idCount++;
	}

	public int getId() {
		return id;
	}

	public List<WorkPlace> getWorkPlaces() {
		return workPlaces;
	}

	public static void setIdCount(int idCount) {
		Garage.idCount = idCount;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_ID).append(this.id).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_WORKPLACES);

		if (this.workPlaces.isEmpty()) {
			sb.append("null");
		} else {

			for (WorkPlace workPlace : workPlaces) {
				sb.append(PATTERN_ID).append(workPlace.getId()).append(PATTERN_ENDFIELD);
			}
		}

		sb.append(PATTERN_ENDFIELD);
		return sb.toString();
	}

}
