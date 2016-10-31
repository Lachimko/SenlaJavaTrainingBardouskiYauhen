package bardouski.senla.training.controllers.services;

import java.util.List;

import bardouski.senla.training.controllers.stores.MechanicStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Mechanic;

public class MechanicService {

	private MechanicStore mechanicStore;

	public MechanicService(DBProcessor dbProcessor) {
		this.mechanicStore = new MechanicStore(dbProcessor);
	}
	
	// Constructors & Getters/Setters
	public MechanicStore getPersonalStore() {
		return mechanicStore;
	}
	
	public void setPersonalStore(MechanicStore personalStore) {
		this.mechanicStore = personalStore;
	}
	// END Constructors & Getters/Setters

	public void add(Mechanic mechanic) {
		mechanicStore.add(mechanic);
	}

	public void remove(Mechanic mechanic) {
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

}