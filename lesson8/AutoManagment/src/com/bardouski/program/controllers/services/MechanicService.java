package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.controllers.stores.IMechanicStore;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IMechanic;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.program.controllers.stores.MechanicStore;
import com.bardouski.propertiesholder.PropertiesContext;

public class MechanicService implements IMechanicService{

	private MechanicStore mechanicStore;

	public MechanicService() throws ClassNotFoundException{
		this.mechanicStore = (MechanicStore) PropertiesContext.getInstance(IMechanicStore.class);
	}

	@Override
	public void add(IMechanic mechanic) {
		mechanicStore.add(mechanic);
	}
	
	@Override
	public void add(String fullName) {
		mechanicStore.add(fullName);
	}
	
	@Override
	public void remove(IMechanic mechanic) throws NoSuchObjectException {
		mechanicStore.remove(mechanic);
	}
	
	@Override
	public Mechanic findFreeMechanic(){
		return mechanicStore.findFreeMechanic();
	}

	@Override
	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicStore.sortMechanicsByFullName();
	}

	@Override
	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicStore.sortMechanicsByCurrentWork();
	}

	@Override
	public Mechanic getMechanic(int id) throws NoSuchObjectException {
		return mechanicStore.getMechanic(id);
	}

	@Override
	public List<Mechanic> getAllMechanics() {
		return mechanicStore.getMechanics();
	}

	@Override
	public void importMechnaics() {
		mechanicStore.importMechnaics();
	}

	@Override
	public void exportMechanics() {
		mechanicStore.exportMechanics();
	}

}
