package com.bardouski.controllers.services;

import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IMechanic;

public interface IMechanicService {

//	void add(IMechanic mechanic);
	
	void add(String fullName);
	
	void remove(int mechanicID) throws NoSuchObjectException;
	
	IMechanic findFreeMechanic() throws NoSuchObjectException;
	
	List<? extends IMechanic> sortMechanicsByFullName();
	
	List<? extends IMechanic> sortMechanicsByCurrentWork();
	
	IMechanic getMechanic(int id) throws NoSuchObjectException;
	
	List<? extends IMechanic> getAllMechanics();
	
	void importMechnaics();
	
	void exportMechanics();

}