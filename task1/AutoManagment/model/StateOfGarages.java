package model;

import java.util.HashSet;
import java.util.Set;

public class StateOfGarages {

	protected String identifier;
	protected Set<Garage> garages = new HashSet<>();

	public StateOfGarages(String identifier) {
		this.identifier = identifier;
	}

	public Set<Garage> getGarages() {
		return garages;
	}

}
