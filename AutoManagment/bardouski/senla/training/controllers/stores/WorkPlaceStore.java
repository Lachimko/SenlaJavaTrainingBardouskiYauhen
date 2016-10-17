package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.List;

import bardouski.senla.training.controllers.converters.Converter;
import bardouski.senla.training.controllers.managers.OrderManager;
import bardouski.senla.training.controllers.stores.abstracts.AbstractStore;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.StateOfGarages;

public class WorkPlaceStore extends AbstractStore<WorkPlaceStore> {

	/**
	 * Convert to String the new instance of Garage constructed with String
	 * garageIdentifier parameter; write converted instance to remote DB(add);
	 * Than we must return refreshed collection to WorkPlaceManager's ArrayList
	 */
	public StateOfGarages createGarage(String garageIdentifier) {

//		Garage template = new Garage(garageIdentifier);
//		String stringGarageTemplate = Converter.serializeGarage(template);
//		
//		OrderManager.getDbDriver().addToRemoteDB(stringGarageTemplate);
//		
//		return getAllGarages();
		
		//add String garage to State
		// write to db
		//return to list new value
		String dbString = OrderManager.getDbDriver().getDBString();

		StateOfGarages stateTemplate = Converter.returnStateOfGaragesFromDB(dbString);
		if (stateTemplate == null) {
			stateTemplate = new StateOfGarages(new ArrayList<Garage>());
		}
		Garage template = new Garage(garageIdentifier);
		
		//add garage to state
		stateTemplate.addGarage(template);
		
		//serialize new state to string
		String newTemplateString = Converter.serializeStateOfGarages(stateTemplate);
		
		//get state from db to rewrite
		String oldStringStateTemplate = Converter.returnStringStateOfGaragesFromDB(dbString);
		
		dbString = dbString.replace(oldStringStateTemplate, newTemplateString);

		//write to remote and syncronize
		OrderManager.getDbDriver().writeToRemoteDB(dbString);

		return stateTemplate;
	}
	
	
	
	public StateOfGarages createStateOfGarage() {

		StateOfGarages stateModel = new StateOfGarages(new ArrayList<Garage>());
		
		String StateOfGarageShape = Converter.serializeStateOfGarages(stateModel);
		OrderManager.getDbDriver().addToRemoteDB(StateOfGarageShape);

		return stateModel;
	}
	
	/** return List of Garages from DB */
	public List<Garage> getAllGarages() {

		return Converter.returnAllGaragesFromDB(OrderManager.getDbDriver().getDBString());
	}
	
	//public void getStateOfGarages
	
	@Override
	public void read(WorkPlaceStore obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(WorkPlaceStore obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String serialize(WorkPlaceStore object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String returnStringFromLocalDB(WorkPlaceStore object) {
		// TODO Auto-generated method stub
		return null;
	}

}
