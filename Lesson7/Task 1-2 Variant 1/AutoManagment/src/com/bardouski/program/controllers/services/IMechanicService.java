package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Mechanic;

public interface IMechanicService {

	void add(Mechanic mechanic);
	
	void remove(Mechanic mechanic) throws NoSuchObjectException;
	
	Mechanic findFreeMechanic();
	
	List<Mechanic> sortMechanicsByFullName();
	
	List<Mechanic> sortMechanicsByCurrentWork();
	
	Mechanic getMechanic(int id) throws NoSuchObjectException;
	
	List<Mechanic> getAllMechanics();
	
	void importMechnaics();
	
	void exportMechanics();

}
