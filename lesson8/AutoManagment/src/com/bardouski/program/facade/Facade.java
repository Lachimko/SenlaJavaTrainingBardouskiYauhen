package com.bardouski.program.facade;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.controllers.services.IOrderService;
import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.facade.IFacade;
import com.bardouski.model.IGarage;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.program.controllers.services.MechanicService;
import com.bardouski.program.controllers.services.OrderService;
import com.bardouski.program.controllers.services.WorkPlaceService;
import com.bardouski.program.dbprocessor.serializator.FacadeResultContainer;
import com.bardouski.propertiesholder.PropertiesContext;

public class Facade implements IFacade {

	private MechanicService mechanicService;
	private WorkPlaceService workPlaceService;
	private OrderService orderService;

	public Facade() throws ClassNotFoundException {
		mechanicService = (MechanicService) PropertiesContext.getInstance(IMechanicService.class);
		workPlaceService = (WorkPlaceService) PropertiesContext.getInstance(IWorkPlaceService.class);
		orderService = (OrderService) PropertiesContext.getInstance(IOrderService.class);
	}

	@Override
	public void setMechanicService(IMechanicService mechanicService) {
		this.mechanicService = (MechanicService) mechanicService;
	}

	@Override
	public void addMechanic(String fullName) {
		this.mechanicService.add(fullName);
	}

	@Override
	public void setWorkPlaceService(IWorkPlaceService workPlaceService) {
		this.workPlaceService = (WorkPlaceService) workPlaceService;
	}

	@Override
	public void setOrderService(IOrderService orderService) {
		this.orderService = (OrderService) orderService;
	}

	@Override
	public void addMechanic(IMechanic mechanic) {
		mechanicService.add(mechanic);
	}

	@Override
	public void removeMechanic(IMechanic mechanic) throws NoSuchObjectException {
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

	@Override
	public void addGarage(IGarage garage) {
		workPlaceService.addGarage(garage);
	}

	@Override
	public void addGarage() {
		workPlaceService.addGarage(new Garage());
	}

	@Override
	public IGarage getGarage(Integer id) throws NoSuchObjectException {
		return workPlaceService.getGarage(id);
	}

	@Override
	public void addWorkPlaceInGarage(IGarage garage) {
		workPlaceService.addWorkPlaceInGarage(garage);
	}

	/** return false if collection had not been affected */
	@Override
	public boolean removeWorkPlaceInGarage(IGarage garage, int workPlaceId) {
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
	public void addOrder(IOrder order) {
		orderService.addOrder(order);
	}

	/**
	 * @throws NoSuchObjectException
	 *             if collection was not affected
	 */
	@Override
	public void removeOrder(IOrder order) throws NoSuchObjectException {
		orderService.removeOrder(order);
	}

	/** Rewrite dates to inputed days forward */
	@Override
	public void replaceDatesOfOrdersFrom(IOrder order, int howManyDays) {
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

	@Override
	public List<Order> returnOrdersSortedByComparator(Comparator<IOrder> comparator) {
		return orderService.returnOrdersSortedByComparator(comparator);
	}

	/*Already synchronized in get methods in its stores*/
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
