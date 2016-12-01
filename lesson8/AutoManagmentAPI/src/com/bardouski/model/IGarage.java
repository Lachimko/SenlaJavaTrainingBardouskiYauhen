package com.bardouski.model;

import java.util.List;

public interface IGarage {

	int getId();

	void setId(int id);

	List<? extends IWorkPlace> getWorkPlaces();

}
