package com.bardouski.program.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Garage implements Serializable{

	private static final long serialVersionUID = -1967004606058120313L;

	private static final String PATTERN_CLASSNAME = "Garage/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_WORKPLACES = "workPlaces=";
	private static final String PATTERN_ENDFIELD = ";";

	private int id;
	private List<WorkPlace> workPlaces = new ArrayList<WorkPlace>();

	// Constructors, Getters/Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public List<WorkPlace> getWorkPlaces() {
		return workPlaces;
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
