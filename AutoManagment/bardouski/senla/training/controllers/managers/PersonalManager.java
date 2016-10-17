package bardouski.senla.training.controllers.managers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import bardouski.senla.training.comparators.MechanicFullNameComparator;
import bardouski.senla.training.controllers.stores.PersonalStore;
import bardouski.senla.training.model.Mechanic;

/**
 * add(Mechanic unit) add(Mechanic...mechanic) add(String mechanicFullName)
 * remove(String) - remove element by String identifier/title remove(Mechanic
 * unit) viewAll()
 */
public class PersonalManager {

	private static PersonalManager instance;

	private PersonalStore personalStore = new PersonalStore();
	private List<Mechanic> mechanics = new ArrayList<Mechanic>();
	
	//Constructors & Getters/Setters
	private PersonalManager() {
		this.setMechanics(personalStore.getAllMechanics());
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}

	public List<Mechanic> getMechanics() {
		return mechanics;
	}
	
	public static PersonalManager getInstance() {

		if (instance == null) {
			return instance = new PersonalManager();
		} else
			return instance;
	}
	
	public PersonalStore getPersonalStore() {
		return personalStore;
	}

	public void setPersonalStore(PersonalStore personalStore) {
		this.personalStore = personalStore;
	}
	
	//END Constructors & Getters/Setters

	/** Add Mechanic into DB, after DB was syncronized, return refreshed List */
	public void add(String mechanicFullName) {

		this.setMechanics(personalStore.create(mechanicFullName));
	}

	
	
	

	public void remove(String mechanicFullName) {

		Iterator<Mechanic> it = this.mechanics.iterator();

		while (it.hasNext()) {

			if (it.next().getFullName().equals(mechanicFullName)) {
				it.remove();
			}
		}
	};

	public void remove(Mechanic unit) {

		Iterator<Mechanic> it = mechanics.iterator();

		while (it.hasNext()) {

			if (it.next().getFullName().equals(unit)) {
				it.remove();
			}
		}
	};

	public void viewAll(Set<Mechanic> mechanics) {

		StringBuilder sb = new StringBuilder();
		Iterator<Mechanic> it = mechanics.iterator();

		while (it.hasNext()) {

			sb.append(returnStringMechanic(it.next()));
		}

		System.out.println(sb);
	}

	public String returnStringMechanic(Mechanic mechanic) {

		StringBuilder sb = new StringBuilder();

		sb.append(mechanic.getFullName()).append(", Current work: ").append(mechanic.getCurrentOrder()).append("\n");

		return sb.toString();
	}

	public void sortByFullName() {

		Set<Mechanic> sortedSet = new TreeSet<>(new MechanicFullNameComparator());

		for (Mechanic mechanic : this.mechanics) {

			sortedSet.add(mechanic);
		}

		viewAll(sortedSet);
	}

	public Set<Mechanic> returnFreeMechanics() {

		Set<Mechanic> freeMechanicsSet = new HashSet<>();

		for (Mechanic mechanic : this.mechanics) {

			if (mechanic.getCurrentOrder() == null) {
				freeMechanicsSet.add(mechanic);
			}
		}

		return (freeMechanicsSet.isEmpty()) ? null : freeMechanicsSet;
	}

	public Mechanic getFreeMechanicFrom(Set<Mechanic> mechanics) {

		if (mechanics != null) {
			Iterator<Mechanic> it = mechanics.iterator();

			return it.next();
		}
		final String MESSAGE = "No free Mechanics are aviable right now.";
		System.out.println(MESSAGE);

		return null;

	}
}
