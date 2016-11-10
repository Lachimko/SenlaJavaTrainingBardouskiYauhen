package com.bardouski.program.facade;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.program.controllers.services.*;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.exceptions.*;
import com.bardouski.program.model.*;
import com.bardouski.program.model.enums.OrderStatus;

public class Facade {

	private static final String NO_DB_FILE = "DB File did not find";

	private Logger logger = Logger.getLogger(Facade.class.getSimpleName());

	private MechanicService mechanicService;
	private WorkPlaceService workPlaceService;
	private OrderService orderService;

	/* Constructors */
	public Facade(String DbFileName) {

		DBProcessor dbProcessor;
		try {
			dbProcessor = new DBProcessor(DbFileName);
			mechanicService = new MechanicService(dbProcessor);
			workPlaceService = new WorkPlaceService(dbProcessor);
			orderService = new OrderService(dbProcessor);
		} catch (FileNotFoundException e) {
			logger.fatal(NO_DB_FILE);
		}
	}
	/* END Constructors */

	/* Mechanics */
	public void addMechanic(Mechanic mechanic) {
		mechanicService.add(mechanic);
	}

	public void removeMechanic(Mechanic mechanic) throws NoSuchObjectException {
		mechanicService.remove(mechanic);
	}

	public Mechanic getMechanic(int id) throws NoSuchObjectException {
		return mechanicService.getMechanic(id);
	}

	public List<Mechanic> getAllMechanics() {
		return mechanicService.getAllMechanics();
	}

	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicService.sortMechanicsByFullName();
	}

	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicService.sortMechanicsByCurrentWork();
	}

	public Mechanic findFreeMechanic() {
		return mechanicService.findFreeMechanic();
	}

	/* WorkPlaces */
	public void addGarage(Garage garage) {
		workPlaceService.addGarage(garage);
	}

	public Garage getGarage(int id) throws NoSuchObjectException {
		return workPlaceService.getGarage(id);
	}

	public void addWorkPlaceInGarage(Garage garage) {
		workPlaceService.addWorkPlaceInGarage(garage);
	}

	/** return false if collection had not been affected */
	public boolean removeWorkPlaceInGarage(Garage garage, int workPlaceId) {
		return workPlaceService.removeWorkPlaceInGarage(garage, workPlaceId);
	}

	/** return first workplace with null order */
	public WorkPlace findFreePlace() throws EmptyCollectionException {
		return workPlaceService.findFreePlace();
	}

	public List<WorkPlace> getFreePlacesInDate(Date date) {
		List<Garage> garages = workPlaceService.getGarages();
		return orderService.getFreePlacesInDate(date, garages);
	}

	/* Orders */
	/**
	 * return Order by ID
	 * 
	 * @throws NoSuchObjectException
	 *             - if no such mechanic was founded
	 */
	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderService.getOrder(orderId);
	}

	/**Return new unique Id from OrderStore. Use for getting clone*/
	public int getNextOrderId(){
		return orderService.getNextOrderId();
	}
	
	/** Add Order to collection */
	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	/**
	 * @throws NoSuchObjectException
	 *             if collection was not affected
	 */
	public void removeOrder(Order order) throws NoSuchObjectException {
		orderService.removeOrder(order);
	}

	/** Rewrite dates to inputed days forward */
	public void replaceDatesOfOrdersFrom(Order order, int howManyDays) {
		orderService.replaceDatesOfOrdersFrom(order, howManyDays);
	}

	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	public List<Order> getAllOrders(OrderStatus orderStatus) {
		return orderService.getAllOrders(orderStatus);
	}

	// Sorted by date lists
	public List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator) {
		return orderService.returnOrdersSortedByComparator(comparator);
	}

	public void saveToFile() {
		orderService.saveToFile(mechanicService.getAllMechanics(), orderService.getAllOrders(),
				workPlaceService.getGarages());
	}

}
