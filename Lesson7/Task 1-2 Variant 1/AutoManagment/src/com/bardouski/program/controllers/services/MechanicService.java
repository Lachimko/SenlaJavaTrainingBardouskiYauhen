package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.controllers.stores.IMechanicStore;
import com.bardouski.program.controllers.stores.MechanicStore;
import com.bardouski.program.exceptions.*;
import com.bardouski.program.model.Mechanic;
import com.bardouski.propertiesholder.PropertiesContext;

public class MechanicService implements IMechanicService{

	private IMechanicStore mechanicStore;

	// Constructors & Getters/Setters

	public MechanicService() throws ClassNotFoundException{
		this.mechanicStore = (MechanicStore) PropertiesContext.getInstance(IMechanicStore.class);
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
