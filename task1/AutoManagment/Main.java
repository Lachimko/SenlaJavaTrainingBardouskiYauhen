import java.util.Collection;

import com.danco.training.TextFileWorker;

import controllers.OrderManager;
import controllers.PersonalManager;
import controllers.TaskManager;
import controllers.WorkPlaceManager;
import model.Garage;
import model.Mechanic;
import model.Order;
import model.StateOfGarages;
import model.Task;
import model.WorkPlace;

public class Main {

	public static void main(String[] args) {

		PersonalManager personal = PersonalManager.getInstance();

		Mechanic mechanicId01 = new Mechanic("Surname Name");
		Mechanic mechanicId02 = new Mechanic("Familia Imia");
		Mechanic mechanicId03 = new Mechanic("Mechanics Name");
		Mechanic mechanicId04 = new Mechanic("Surname0 Name0");

		personal.add(mechanicId01, mechanicId02, mechanicId03, mechanicId04);
		//personal.viewAll();
		//System.out.println("*********************");

//		personal.remove("Familia Imia");
//		personal.viewAll();

		WorkPlace gar01wp01 = new WorkPlace("g01wp01");
		WorkPlace gar01wp02 = new WorkPlace("g01wp02");
		WorkPlace gar01wp03 = new WorkPlace("g01wp03");
		WorkPlace gar01wp04 = new WorkPlace("g01wp04");

		WorkPlace gar02wp01 = new WorkPlace("g02wp01");
		WorkPlace gar02wp02 = new WorkPlace("g02wp02");
		WorkPlace gar02wp03 = new WorkPlace("g02wp03");
		WorkPlace gar02wp04 = new WorkPlace("g02wp04");

		Garage garage1 = new Garage("g01");
		Garage garage2 = new Garage("g02");

		garage1.addWorkPlaces(gar01wp01, gar01wp02, gar01wp03, gar01wp04);
		garage2.addWorkPlaces(gar02wp01, gar02wp02, gar02wp03, gar02wp04);

		StateOfGarages garagePark = new StateOfGarages("FixCenter");

		WorkPlaceManager workPlaceManager = WorkPlaceManager.getInstance();
		workPlaceManager.setStateOfGarages(garagePark);
		workPlaceManager.addGarages(garage1, garage2);

		workPlaceManager.addWorkPlaceInGivenGarage("g02wp05", garage2);
		//workPlaceManager.viewAllWorkPlaces();
		workPlaceManager.removeWorkPlace("g02wp01");
		//workPlaceManager.viewAllWorkPlaces();

		TaskManager tm = TaskManager.getInstance();
		tm.viewTask();
		
//		Task task = new Task("fix Reanualt Laguna bumber", "25 03 2016");
//		Order order = new Order(task);
//		mechanicId01.setCurrentOrder(order);
//		personal.viewAll();
		
		//workPlaceManager.viewAllFreeWorkPlaces();
		
//		personal.sortByFullName();
//		System.out.println("***********");
		
		OrderManager orderManager = OrderManager.init(personal, workPlaceManager, tm);
		orderManager.createOrder("fix BMW", "10 13 2016", "12 10 2016", "12 11 2016", 558.5);
		orderManager.createOrder("Order todo N2", "10 13 2016", "12 10 2016", "12 11 2016", 1000);
		orderManager.createOrder("Order todo N3", "10 13 2016", "12 10 2016", "12 11 2016", 1000);
		orderManager.createOrder("Order todo N4", "10 13 2016", "12 10 2016", "12 11 2016", 1000);
		orderManager.createOrder("ДУАЕ", "10 13 2016", "12 10 2016", "12 11 2016", 1000);
		orderManager.viewOrders();
		personal.viewAll();
		workPlaceManager.viewAllWorkPlaces();
		orderManager.viewOrders();
		personal.viewAll();
		workPlaceManager.viewAllWorkPlaces();
		System.out.println(personal.returnFreeMechanics());
		personal.sortByFullName();
	}

}
