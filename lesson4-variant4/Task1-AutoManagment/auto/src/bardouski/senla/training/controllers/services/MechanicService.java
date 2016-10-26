package bardouski.senla.training.controllers.services;

import java.util.List;

import bardouski.senla.training.controllers.stores.MechanicStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class MechanicService {

	private MechanicStore mechanicStore;

	// Constructors & Getters/Setters
//	public MechanicService(List<Mechanic> listFromDB) {
//		this.mechanicStore = new MechanicStore(listFromDB);
//	}
	
	public MechanicService(DBProcessor dbProcessor) {
		this.mechanicStore = new MechanicStore(dbProcessor);
	}
	
	public MechanicStore getPersonalStore() {
		return mechanicStore;
	}
	
	public void setPersonalStore(MechanicStore personalStore) {
		this.mechanicStore = personalStore;
	}

	// END Constructors & Getters/Setters

	public void add(String mechanicFullName) {

		mechanicStore.add(mechanicFullName);
	}

	public void remove(int id) {

		mechanicStore.remove(id);

	};
	
	public Mechanic findFreeMechanic(){
		return mechanicStore.findFreeMechanic();
		
	}

	public Order returnOrderOfMechanic(int mechanicId) {
		return mechanicStore.returnOrderOfMechanic(mechanicId);
	}

	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicStore.sortMechanicsByFullName();
	}

	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicStore.sortMechanicsByCurrentWork();
	}

}
