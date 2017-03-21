package com.bardouski.model;

import java.util.List;

import com.bardouski.model.impl.WorkPlace;

public interface IGarage extends EntityModel {

	Integer getId();

	void setId(Integer id);

	List<? extends IWorkPlace> getWorkPlaces();

	void setWorkPlaces(List<WorkPlace> workPlaces);

}
