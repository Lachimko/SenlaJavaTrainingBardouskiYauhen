package bardouski.senla.training.main;
import java.util.ArrayList;
import java.util.List;

import bardouski.senla.training.controllers.converters.Converter;
import bardouski.senla.training.controllers.managers.OrderManager;
import bardouski.senla.training.controllers.stores.OrderStore;
import bardouski.senla.training.controllers.stores.PersonalStore;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.StateOfGarages;
import bardouski.senla.training.model.db.DBDriver;

public class Main {

	public static void main(String[] args) {

		OrderManager orderManager = OrderManager.init();
		//orderManager.getWorkPlaceManager().
		orderManager.getWorkPlaceManager().addGarage("g4");
		orderManager.getWorkPlaceManager().addGarage("g5");
//		List<Garage> gar = Converter.returnAllGaragesFromDB(orderManager.getDbDriver().getDBString());
//		
//		for (Garage garage : gar) {
//			System.out.println(garage);
//		}
//		String sg = Converter.serializeStateOfGarages(new StateOfGarages(new ArrayList<Garage>()));
//		System.out.println(sg);
		ArrayList<Garage> garages = (ArrayList<Garage>)Converter.returnAllGaragesFromDB(OrderManager.getDbDriver().getDBString());
		
		for (Garage garage : garages) {
			System.out.print(garage);
		}
		
		StateOfGarages sg = orderManager.getWorkPlaceManager().getStateOfGarages();
		
		System.out.println(sg);
		//StateOfGarages s = Converter.returnStateOfGaragesFromDB(orderManager.getDbDriver().getDBString());
		//System.out.println(s);
		//Converter.returnAllGaragesFromDB("@Mechanic1{id:1;fullName:'проверка проверка';currentOrder:null;}@Garage1{id:1;identifier:'g1';workPlaces:[];}@Mechanic2{id:2;fullName:'проверка2 проверка2';currentOrder:null;}@Garage2{id:2;identifier:'g2';workPlaces:[];}");
	//	deserializeGarage("@Garage1{id:1;identifier:'MainFixStation';workPlaces:[];}");
		
		//orderManager.getWorkPlaceManager().
		//orderManager.getWorkPlaceManager().addGarages("asfasfas");
		//Add Personal
		//orderManager.getPersonalManager().add("проверка проверка");
		//orderManager.getPersonalManager().add("проверка2 проверка2");
		
		// Garage garage1 = new Garage("g01");
		// Garage garage2 = new Garage("g02");
		// StateOfGarages garagePark = new StateOfGarages("FixCenter");
		//
		// orderManager.getWorkPlaceManager().addGarages(garage1, garage2);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g01wp01",
		// garage1);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g01wp02",
		// garage1);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g01wp03",
		// garage1);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g01wp04",
		// garage1);
		//
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g02wp01",
		// garage2);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g02wp02",
		// garage2);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g02wp03",
		// garage2);
		// orderManager.getWorkPlaceManager().addWorkPlaceInGivenGarage("g02wp04",
		// garage2);
		//
		// orderManager.createOrder("fix BMW", "10 13 2016", "12 10 2016", "12
		// 11 2016", 558.5);
		// orderManager.createOrder("Order todo N2", "10 13 2016", "12 10 2016",
		// "12 11 2016", 1000);
		// orderManager.createOrder("Order todo N3", "10 13 2016", "12 10 2016",
		// "12 11 2016", 1000);
		// orderManager.createOrder("Order todo N4", "10 13 2016", "12 10 2016",
		// "12 11 2016", 1000);
		// orderManager.createOrder("ДУАЕ", "10 13 2016", "12 10 2016", "12 11
		// 2016", 1000);

		//Mechanic mach = new Mechanic("1");
		//ArrayList list = (ArrayList) TextTransformer.returnObj(mach);
		
		
		//String[] s = orderManager.getPersonalManager().getPersonalStore().getAllMechanicsFromRemote();
		
		ArrayList<Mechanic> m = (ArrayList<Mechanic>)Converter.returnAllMechanicsFromDB(orderManager.getDbDriver().getDBString());

		for (Mechanic mechanic : m) {
			System.out.println(mechanic);
		}
		//DBDriver.makeConnection("d:/temp.txt");
		//DBDriver.view();
		//DBReader.
		//System.out.println(DBDriver.serializeMechanic(mech1));
		
		DBDriver driver = new DBDriver();
		driver.init("d:/temp.txt");
	//	ArrayList<String> list = driver.getAllInstancesFromTxt();
		
//		for (String string : list) {
//			System.out.println(string);
//		}
//		
		OrderStore os = new OrderStore();
//		Order o = os.deserialize("{!@Order@id:1;mechanic:{!@Mechanic2@id:15558;fullName:'master numbertwo';currentOrder:313rnullfdf;};}");
		//Mechanic m1 = ps.deserialize("{!@Mechanic2@id:15558;fullName:'master numbertwo';currentOrder:313rnullfdf;}");
//		System.out.println(m1.getId());
//		System.out.println(m1.getFullName());
//		System.out.println(m1.getCurrentOrder());
		//		String[] s = new String [0];
//		System.out.println(s.length);
		// indexOf(String str, int fromIndex)
		// int val = string.indexOf("}");
		// String obj = string.substring(string.indexOf("{!@") + 3,
		// string.indexOf("}"));
		// string.su
		// System.out.println(obj);
		// System.out.println("last } is in position: " + startIndex);
		// Pattern p = Pattern.compile("{!@Mechanic*}$");
		// Matcher m = p.matcher(string);
		// System.out.println(m.matches());

	}

}
