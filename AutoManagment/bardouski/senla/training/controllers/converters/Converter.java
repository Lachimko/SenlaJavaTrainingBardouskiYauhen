package bardouski.senla.training.controllers.converters;

import java.util.ArrayList;
import java.util.List;

import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.StateOfGarages;
import bardouski.senla.training.model.WorkPlace;

public class Converter {

	/* MECHANIC CONVERTER */
	/** Convert Mechanic to String DB-format value */
	public static String serializeMechanic(Mechanic mechanic) {

		StringBuilder sb = new StringBuilder();

		sb.append("@Mechanic").append(mechanic.getId()).append("{");
		sb.append("id:").append(mechanic.getId()).append(";");
		sb.append("fullName:").append("'").append(mechanic.getFullName()).append("';");
		sb.append("currentOrder:").append(mechanic.getCurrentOrder()).append(";");
		sb.append("}");

		return sb.toString();
	}

	/**
	 * Return List<Mechanics>. Copy DB String; find all Mechanics in; convert
	 * Strings to Mechanic objects
	 */
	public static List<Mechanic> returnAllMechanicsFromDB(String dbString) {

		final String className_pattern = "@Mechanic";
		final String objectStart_pattern = "{";
		final String objectEnd_pattern = "}";
		int startFieldPosition = 0;
		int endFieldPosition = 0;

		List<String> list = new ArrayList<String>();

		while (true) {

			startFieldPosition = dbString.indexOf(className_pattern, startFieldPosition);
			if (startFieldPosition < 0) {
				break;
			}
			endFieldPosition = dbString.indexOf(objectEnd_pattern, startFieldPosition);

			list.add(dbString.substring(dbString.indexOf(objectStart_pattern, startFieldPosition),
					endFieldPosition + 1));
			startFieldPosition = endFieldPosition;
		}

		return convertStringToMechanic(list);
	}

	/** Convert List<String> to List<Mechanics> */
	public static List<Mechanic> convertStringToMechanic(List<String> list) {

		List<Mechanic> mechanics = new ArrayList<Mechanic>();

		for (String string : list) {

			mechanics.add(deserializeMechanic(string));
		}

		return mechanics;
	}

	/** Convert StringMechanic to Mechanic */
	public static Mechanic deserializeMechanic(String StringMechanic) {

		final String id_pattern = "id:";
		final String fullName_pattern = "fullName:";
		final String order_pattern = "currentOrder:";
		final String fieldEnd_pattern = ";";

		int mechanicId;
		int startFieldPosition;
		int endFieldPosition;

		String mechanicFullName;
		String mechanicOrderString;
		Order mechanicOrder = null;

		// mechanic ID parse
		startFieldPosition = StringMechanic.indexOf(id_pattern) + id_pattern.length();
		endFieldPosition = StringMechanic.indexOf(fieldEnd_pattern, startFieldPosition);
		mechanicId = Integer.parseInt((StringMechanic.substring(startFieldPosition, endFieldPosition)));

		// mechanic FULLNAME parse
		startFieldPosition = StringMechanic.indexOf(fullName_pattern) + fullName_pattern.length();
		endFieldPosition = StringMechanic.indexOf(fieldEnd_pattern, startFieldPosition);
		mechanicFullName = StringMechanic.substring(startFieldPosition, endFieldPosition);
		// remove " ' " behind string value
		mechanicFullName = mechanicFullName.substring(1, (mechanicFullName.length() - 1));

		// mechanic CURRENTORDER parse
		startFieldPosition = StringMechanic.indexOf(order_pattern) + order_pattern.length();
		endFieldPosition = StringMechanic.indexOf(fieldEnd_pattern, startFieldPosition);
		mechanicOrderString = StringMechanic.substring(startFieldPosition, endFieldPosition);

		if (!mechanicOrderString.contains("null")) {
			// find in db
			// serialize order
		}

		return new Mechanic(mechanicId, mechanicFullName, mechanicOrder);
	}

	/* GARAGE CONVERTER */
	/** Convert Mechanic to String DB-format value */
	public static String serializeGarage(Garage garage) {

		StringBuilder sb = new StringBuilder();

		sb.append("@Garage").append(garage.getId()).append("{");
		sb.append("id:").append(garage.getId()).append(";");
		sb.append("identifier:").append("'").append(garage.getIdentifier()).append("';");
		// if garage.getWorkPlaces() is Empty - returns to sb [] automaticly
		if (garage.getWorkPlaces().isEmpty()){
			sb.append("workPlaces:").append("null");
		} else {
			sb.append("workPlaces:").append(garage.getWorkPlaces());	
		}
		
		sb.append(";}");

		return sb.toString();
	}

	/**
	 * Return List<Garage>. Copy DB String; find all garages in; convert Strings
	 * to Garage objects
	 */
	public static List<Garage> returnAllGaragesFromDB(String dbString) {

		final String className_pattern = "@Garage";
		final String objectStart_pattern = "{";
		final String objectEnd_pattern = "}";
		int startFieldPosition = 0;
		int endFieldPosition = 0;

		List<String> list = new ArrayList<String>();

		while (true) {

			startFieldPosition = dbString.indexOf(className_pattern, startFieldPosition);
			if (startFieldPosition < 0) {
				break;
			}
			endFieldPosition = dbString.indexOf(objectEnd_pattern, startFieldPosition);

			list.add(dbString.substring(dbString.indexOf(objectStart_pattern, startFieldPosition),
					endFieldPosition + 1));
			startFieldPosition = endFieldPosition;
		}

		return convertStringToGarages(list);
	}

	/** Convert List<String> to List<Garage> */
	public static List<Garage> convertStringToGarages(List<String> list) {

		List<Garage> garages = new ArrayList<Garage>();

		for (String string : list) {

			garages.add(deserializeGarage(string));
		}

		return garages;
	}

	/** Convert StringMechanic to Mechanic */
	// not finished deserializing of workplases
	public static Garage deserializeGarage(String stringGarage) {

		final String id_pattern = "id:";
		final String identifier_pattern = "identifier:";
		final String workPlaces_pattern = "workPlaces:";
		final String fieldEnd_pattern = ";";

		int garageId;
		int startFieldPosition;
		int endFieldPosition;

		String garageIdentifier;
		String garageWorkPlacesString;
		List<WorkPlace> garageWorkPlaces = null;

		// garage ID parse
		startFieldPosition = stringGarage.indexOf(id_pattern) + id_pattern.length();
		endFieldPosition = stringGarage.indexOf(fieldEnd_pattern, startFieldPosition);
		garageId = Integer.parseInt((stringGarage.substring(startFieldPosition, endFieldPosition)));

		// garage IDENTIFIER parse
		startFieldPosition = stringGarage.indexOf(identifier_pattern) + identifier_pattern.length();
		endFieldPosition = stringGarage.indexOf(fieldEnd_pattern, startFieldPosition);
		garageIdentifier = stringGarage.substring(startFieldPosition, endFieldPosition);
		// remove " ' " behind string value
		garageIdentifier = garageIdentifier.substring(1, (garageIdentifier.length() - 1));

		// garage WORKPLACES[] parse
		startFieldPosition = stringGarage.indexOf(workPlaces_pattern) + workPlaces_pattern.length();
		endFieldPosition = stringGarage.indexOf(fieldEnd_pattern, startFieldPosition);
		garageWorkPlacesString = stringGarage.substring(startFieldPosition, endFieldPosition);
		// remove " [ ] " before and behind string value
		garageWorkPlacesString = garageWorkPlacesString.substring(1, (garageWorkPlacesString.length() - 1));

		if (!garageWorkPlacesString.isEmpty()) {
			// find in db
			// serialize workplaces
		}
		return new Garage(garageId, garageIdentifier, garageWorkPlaces);
	}

	/* STATEOFGARAGES CONVERTER */
	/** Convert StateOfGarages to String DB-format value */
	public static String serializeStateOfGarages(StateOfGarages stateOfGarages) {

		StringBuilder sb = new StringBuilder();

		sb.append("@StateOfGarages").append("{");
		// if garage.getWorkPlaces() is Empty - returns to sb [] automaticly
		sb.append("garages:");

		if (stateOfGarages.getGarages().isEmpty()) {
			sb.append("null");
		} else {

			for (Garage garage : stateOfGarages.getGarages()) {
				sb.append(serializeGarage(garage));
			}
		}

		sb.append(";}");

		return sb.toString();
	}

	/**
	 * Return StateOfGarages. Copy DB String; find stateOfGarages in; convert
	 * Strings to StateOfGarages object. Return null if no StateOfGarages in db
	 */
	public static StateOfGarages returnStateOfGaragesFromDB(String dbString) {

		final String className_pattern = "@StateOfGarages";
		final String objectStart_pattern = "{";
		final String objectEnd_pattern = "}";
		String stateOfGaragesString = null;

		int startFieldPosition = dbString.indexOf(className_pattern, 0);

		if (startFieldPosition > -1) {

			int objectEnd_patternsPosition = startFieldPosition + className_pattern.length();
			int endFieldPosition = 0;
			
			int opens = 0;
			int closes = 0;
			char open = '{';
			char close = '}';
			
			for (int i = objectEnd_patternsPosition; i < dbString.length(); i++) {
				
				char current = dbString.charAt(i);
				
				
				if (current == open) {opens++;}
				if (current == close) {closes++;}
				
				if (opens != 0) {
					if (opens == closes) {endFieldPosition = i; break;}
				}
			}
			
			//endFieldPosition = dbString.indexOf(objectEnd_pattern, startFieldPosition);

			stateOfGaragesString = dbString.substring(startFieldPosition, endFieldPosition + 1);
		}

		return (stateOfGaragesString == null) ? null : deserializeStateOfGarage(stateOfGaragesString);
	}

	/** Convert StringMechanic to Mechanic */
	// not finished deserializing of workplases
	public static StateOfGarages deserializeStateOfGarage(String stringStateOfGarages) {

		if (stringStateOfGarages.contains("garages:null")) {
			return new StateOfGarages(new ArrayList<Garage>());
		}
		
		final String garages_pattern = "garages:";
		final String fieldEnd_pattern = ";";

		int startFieldPosition;
		int endFieldPosition;

		// stateOfGarages GARAGES[] parse
		startFieldPosition = stringStateOfGarages.indexOf(garages_pattern) + garages_pattern.length();
		endFieldPosition = stringStateOfGarages.indexOf(fieldEnd_pattern, startFieldPosition) - 1;
		stringStateOfGarages = stringStateOfGarages.substring(startFieldPosition, endFieldPosition);
		// remove " [ ] " before and behind string value
		stringStateOfGarages = stringStateOfGarages.substring(1, (stringStateOfGarages.length() - 1));

		if (!stringStateOfGarages.isEmpty()) {
			// find in db
			// serialize garages
			return null;
		}

		return new StateOfGarages(new ArrayList<Garage>());
	}

	public static String returnStringStateOfGaragesFromDB(String dbString) {

		final String className_pattern = "@StateOfGarages";
		final String objectStart_pattern = "{";
		final String objectEnd_pattern = "}";
		String stateOfGaragesString = null;

		int startFieldPosition = dbString.indexOf(className_pattern, 0);

		if (startFieldPosition > -1) {

			int endFieldPosition = dbString.indexOf(objectEnd_pattern, startFieldPosition);

			stateOfGaragesString = dbString.substring(dbString.indexOf(objectStart_pattern, startFieldPosition),
					endFieldPosition + 1);
		}

		return className_pattern + stateOfGaragesString;

	}
}
