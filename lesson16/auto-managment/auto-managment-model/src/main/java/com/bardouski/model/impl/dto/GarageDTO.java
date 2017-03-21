package com.bardouski.model.impl.dto;

import java.util.ArrayList;
import java.util.List;

import com.bardouski.model.dto.AutoMngmtDTO;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;

public class GarageDTO implements AutoMngmtDTO {

	private static final long serialVersionUID = -1967004606058120313L;

	private Integer id;
	private List<Integer> workPlaces = new ArrayList<Integer>();

	public GarageDTO(Garage garage) {
		id = garage.getId();
		for (WorkPlace workPlace : garage.getWorkPlaces()) {
			workPlaces.add(workPlace.getId());
		}
	}

	public GarageDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getWorkPlaces() {
		return workPlaces;
	}

	public void setWorkPlaces(List<Integer> workPlaces) {
		this.workPlaces = workPlaces;
	}

}
