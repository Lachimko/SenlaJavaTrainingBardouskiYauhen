package com.bardouski.model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IGarage;

@PrintableObject(name = "Garage")
public class Garage implements Serializable, IGarage {

	private static final long serialVersionUID = -1967004606058120313L;

	@Printable(name = "garageID", isDetailedOnly = false, order = 0)
	private int id;
	@PrintableRef(name = "garageWorkPlacesList", isDetailedView = false, order = 1, isRecursive = true)
	private List<WorkPlace> workPlaces = new ArrayList<WorkPlace>();

	public Garage() {
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public List<WorkPlace> getWorkPlaces() {
		return workPlaces;
	}

}
