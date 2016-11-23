package com.bardouski.program.facade;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.program.controllers.services.*;
import com.bardouski.program.dbprocessor.serializator.FacadeResultContainer;
import com.bardouski.program.exceptions.*;
import com.bardouski.program.model.*;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.propertiesholder.PropertiesContext;

public class Facade implements IFacade {

	private IMechanicService mechanicService;
	private IWorkPlaceService workPlaceService;
	private IOrderService orderService;

	/* Constructors */
	public Facade() throws ClassNotFoundException {

		mechanicService = (MechanicService) PropertiesContext.getInstance(IMechanicService.class);
		workPlaceService = (WorkPlaceService) PropertiesContext.getInstance(IWorkPlaceService.class);
		orderService = (OrderService) PropertiesContext.getInstance(IOrderService.class);
	}

	/* END Constructors */

	public void setMechanicService(IMechanicService mechanicService) {
		this.mechanicService = mechanicService;
	}

	public void setWorkPlaceService(IWorkPlaceService workPlaceService) {
		this.workPlaceService = workPlaceService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	/* Mechanics */
	@Override
	public void addMechanic(Mechanic mechanic) {
		mechanicService.add(mechanic);
	}

	@Override
	public void removeMechanic(Mechanic mechanic) throws NoSuchObjectException {
		mechanicService.remove(mechanic);
	}

	@Override
	public Mechanic getMechanic(int id) throws NoSuchObjectException {
		return mechanicService.getMechanic(id);
	}

	@Override
	public List<Mechanic> getAllMechanics() {
		return mechanicService.getAllMechanics();
	}

	@Override
	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicService.sortMechanicsByFullName();
	}

	@Override
	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicService.sortMechanicsByCurrentWork();
	}

	@Override
	public Mechanic findFreeMechanic() {
		return mechanicService.findFreeMechanic();
	}

	/* WorkPlaces */
	@Override
	public void addGarage(Garage garage) {
		workPlaceService.addGarage(garage);
	}

	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {
		return workPlaceService.getGarage(id);
	}

	@Override
	public void addWorkPlaceInGarage(Garage garage) {
		workPlaceService.addWorkPlaceInGarage(garage);
	}

	/** return false if collection had not been affected */
	@Override
	public boolean removeWorkPlaceInGarage(Garage garage, int workPlaceId) {
		return workPlaceService.removeWorkPlaceInGarage(garage, workPlaceId);
	}

	/** return first workplace with null order */
	@Override
	public WorkPlace findFreePlace() throws EmptyCollectionException {
		return workPlaceService.findFreePlace();
	}

	@Override
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
	@Override
	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderService.getOrder(orderId);
	}

	/** Return new unique Id from OrderStore. Use for getting clone */
	@Override
	public int getNextOrderId() {
		return orderService.getNextOrderId();
	}

	/** Add Order to collection */
	@Override
	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	/**
	 * @throws NoSuchObjectException
	 *             if collection was not affected
	 */
	@Override
	public void removeOrder(Order order) throws NoSuchObjectException {
		orderService.removeOrder(order);
	}

	/** Rewrite dates to inputed days forward */
	@Override
	public void replaceDatesOfOrdersFrom(Order order, int howManyDays) {
		orderService.replaceDatesOfOrdersFrom(order, howManyDays);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@Override
	public List<Order> getAllOrders(OrderStatus orderStatus) {
		return orderService.getAllOrders(orderStatus);
	}

	// Sorted by date lists
	@Override
	public List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator) {
		return orderService.returnOrdersSortedByComparator(comparator);
	}

	@Override
	public void saveToFile() {
		orderService.saveToFile(new FacadeResultContainer(mechanicService.getAllMechanics(),
				orderService.getAllOrders(), workPlaceService.getGarages()));
	}

	@Override
	public void importMechnaics() {
		mechanicService.importMechnaics();
	}

	@Override
	public void exportMechanics() {
		mechanicService.exportMechanics();
	}

}
