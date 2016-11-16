package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.model.Mechanic;

public interface IMechanicService {

	void add(Mechanic mechanic);
	
	void remove(Mechanic mechanic) throws Exception;
	
	Mechanic findFreeMechanic();
	
	List<Mechanic> sortMechanicsByFullName();
	
	List<Mechanic> sortMechanicsByCurrentWork();
	
	Mechanic getMechanic(int id) throws Exception;
	
	List<Mechanic> getAllMechanics();
	
	void importMechnaics();
	
	void exportMechanics();

}
