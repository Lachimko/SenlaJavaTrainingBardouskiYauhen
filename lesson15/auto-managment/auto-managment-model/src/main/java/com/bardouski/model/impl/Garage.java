package com.bardouski.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.bardouski.model.IGarage;

@Entity
public class Garage implements IGarage {

	private static final long serialVersionUID = -1967004606058120313L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@JoinTable(joinColumns = @JoinColumn(name = "garage_id"), inverseJoinColumns = @JoinColumn(name = "workplace_id"))
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
