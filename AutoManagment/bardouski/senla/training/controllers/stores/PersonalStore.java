package bardouski.senla.training.controllers.stores;

import java.util.List;

import bardouski.senla.training.controllers.converters.Converter;
import bardouski.senla.training.controllers.managers.OrderManager;
import bardouski.senla.training.controllers.stores.abstracts.AbstractStore;
import bardouski.senla.training.model.Mechanic;

public class PersonalStore extends AbstractStore<Mechanic> {

	/**
	 * Convert to String the new instance of Mechanic constructed with String
	 * mechanicFullName parameter; write converted instance to remote DB(add);
	 * Than we must return refreshed collection to PersonalManager's ArrayList
	 */
	public List<Mechanic> create(String mechanicFullName) {

		String mechanicShape = Converter.serializeMechanic(new Mechanic(mechanicFullName));
		OrderManager.getDbDriver().addToRemoteDB(mechanicShape);

		return getAllMechanics();
	}

	/** return List of Mechanics from DB */
	public List<Mechanic> getAllMechanics() {

		return Converter.returnAllMechanicsFromDB(OrderManager.getDbDriver().getDBString());
	}

	
	
	
//	@Override
//	public void create(Mechanic obj) {
//
//		String current = Converter.serializeMechanic(obj);
//		String dbString = this.dbDriver.getDBString();
//
//		boolean contains = dbString.contains(current);
//
//		if (contains == true) {
//			// достать объект из локальной базы
//			//
//			// заменить то что было на новый объект
//		} else {
//
//			dbDriver.writeToRemoteDB(current);
//		}
//
//		// сериализировать в строку
//		// проверить есть ли такой в базе
//		// передать в драйвер
//
//	}

	@Override
	public void read(Mechanic obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Mechanic obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public String serialize(Mechanic mechanic) {

		StringBuilder sb = new StringBuilder();

		sb.append("{!@Mechanic").append(mechanic.getId()).append("@");
		sb.append("id:").append(mechanic.getId()).append(";");
		sb.append("fullName:").append("'").append(mechanic.getFullName()).append("';");
		sb.append("currentOrder:").append(mechanic.getCurrentOrder()).append("';");
		sb.append("}");

		return sb.toString();
	}

	@Override
	protected String returnStringFromLocalDB(Mechanic object) {
		// TODO Auto-generated method stub
		return null;
	}

}
