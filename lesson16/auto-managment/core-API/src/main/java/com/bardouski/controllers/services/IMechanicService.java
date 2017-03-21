package com.bardouski.controllers.services;

import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IMechanic;
import com.bardouski.model.impl.dto.MechanicDTO;

public interface IMechanicService {

	void add(String fullName);
	
	void remove(int mechanicID) throws NoSuchObjectException;
	
	IMechanic findFreeMechanic() throws NoSuchObjectException;
	
	MechanicDTO getMechanic(int id) throws NoSuchObjectException;
	
	List<MechanicDTO> getAllMechanics(String sortParameter);
	
	void importMechnaics();
	
	void exportMechanics();

}
