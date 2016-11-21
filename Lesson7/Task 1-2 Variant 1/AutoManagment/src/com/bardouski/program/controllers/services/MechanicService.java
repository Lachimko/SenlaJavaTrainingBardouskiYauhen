package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.controllers.stores.MechanicStore;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.exceptions.*;
import com.bardouski.program.model.Mechanic;

public class MechanicService implements IMechanicService{

	private MechanicStore mechanicStore;

	// Constructors & Getters/Setters
	public MechanicService(DBProcessor dbProcessor) {
		this.mechanicStore = new MechanicStore(dbProcessor);
	}
	
	public MechanicService(){
	}
	// END Constructors & Getters/Setters

	public void add(Mechanic mechanic) {
		mechanicStore.add(mechanic);
	}

	public void remove(Mechanic mechanic) throws NoSuchObjectException {
		mechanicStore.remove(mechanic);
	}
	
	public Mechanic findFreeMechanic(){
		return mechanicStore.findFreeMechanic();
	}

	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicStore.sortMechanicsByFullName();
	}

	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicStore.sortMechanicsByCurrentWork();
	}

	public Mechanic getMechanic(int id) throws NoSuchObjectException {
		return mechanicStore.getMechanic(id);
	}

	public List<Mechanic> getAllMechanics() {
		return mechanicStore.getMechanics();
	}

	public void importMechnaics() {
		mechanicStore.importMechnaics();
	}

	public void exportMechanics() {
		mechanicStore.exportMechanics();
	}

}
