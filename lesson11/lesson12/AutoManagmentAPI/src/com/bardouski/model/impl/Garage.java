package com.bardouski.model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IGarage;

@PrintableObject(name = "Garage")
@Entity
public class Garage implements Serializable, IGarage {

	private static final long serialVersionUID = -1967004606058120313L;

	@Printable(name = "garageID", isDetailedOnly = false, order = 0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_garage")
	private Integer id;

	@PrintableRef(name = "garageWorkPlacesList", isDetailedView = false, order = 1, isRecursive = true)
	@OneToMany(mappedBy="garage", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<WorkPlace> workPlaces = new ArrayList<WorkPlace>();

	public Garage() {
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public List<WorkPlace> getWorkPlaces() {
		return workPlaces;
	}

	@Override
	public void setWorkPlaces(List<WorkPlace> workPlaces) {
		this.workPlaces = workPlaces;
	}

}
