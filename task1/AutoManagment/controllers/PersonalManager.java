package controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import model.Mechanic;
import model.comparators.MechanicFullNameComparator;
import model.comparators.MechanicWorkingStatusComparator;

/**
 * add(Mechanic unit) add(Mechanic...mechanic) add(String mechanicFullName)
 * remove(String) - remove element by String identifier/title remove(Mechanic
 * unit) viewAll()
 */
public class PersonalManager {

	private static PersonalManager instance;

	private Set<Mechanic> mechanics = new HashSet<>();

	private PersonalManager() {
	}

	public Set<Mechanic> getMechanics() {
		return mechanics;
	}

	public static PersonalManager getInstance() {

		if (instance == null) {
			return instance = new PersonalManager();
		} else
			return instance;
	}

	public void add(String mechanicFullName) {

		this.add(new Mechanic(mechanicFullName));
	}

	public void add(Mechanic unit) {
		this.mechanics.add(unit);
	}

	public void add(Mechanic... mechanics) {
		for (Mechanic mechanic : mechanics) {
			this.mechanics.add(mechanic);
		}
	};

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

	public void viewAll() {

		final String MESSAGE = "ALL STATE MECHANICS:";

		System.out.println(MESSAGE);
		viewAll(this.mechanics);
	}

	public void viewAll(Set<Mechanic> mechanics) {

		StringBuilder sb = new StringBuilder();
		Iterator<Mechanic> it = mechanics.iterator();

		while (it.hasNext()) {

			sb.append(returnStringMechanic(it.next()));
		}

		System.out.println(sb);
	}
	
	public String returnStringMechanic(Mechanic mechanic){
		
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
	
	public Mechanic getFreeMechanicFrom(Set<Mechanic> mechanics){
		
		if (mechanics != null){
			Iterator<Mechanic> it = mechanics.iterator();
			
			return it.next();
		} 			
			final String MESSAGE = "No free Mechanics are aviable right now.";
			System.out.println(MESSAGE);
		
		return null;
		
	}
}
