package bardouski.senla.training.model;

import java.util.ArrayList;
import java.util.List;

public class StateOfGarages {

	protected List<Garage> garages = new ArrayList<Garage>();

	public StateOfGarages(List<Garage> garages) {
		this.garages = garages;
	}

	public List<Garage> getGarages() {
		return garages;
	}

	public void setGarages(List<Garage> garages) {
		this.garages = garages;
	}

	public void addGarage(Garage garage){
		garages.add(garage);
	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("StateOfGarages{");
		sb.append("garages:[ ");
		
		for (Garage garage : garages) {
			sb.append(garage);
		}
		
		sb.append("]}\n");
		return sb.toString();
		
	}
	
	
	
}
