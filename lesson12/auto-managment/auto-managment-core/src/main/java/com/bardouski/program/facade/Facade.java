package com.bardouski.program.facade;

import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.controllers.services.IOrderService;
import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.facade.IFacade;
import com.bardouski.model.IGarage;
import com.bardouski.model.ITask;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.program.controllers.services.MechanicService;
import com.bardouski.program.controllers.services.OrderService;
import com.bardouski.program.controllers.services.WorkPlaceService;
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
	public void createMechanic(String fullName) {
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
	public void removeMechanic(int mechanicID) throws NoSuchObjectException {
		mechanicService.remove(mechanicID);
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
	public Mechanic findFreeMechanic() throws NoSuchObjectException {
		return mechanicService.findFreeMechanic();
	}

	@Override
	public void createGarage() {
		workPlaceService.addGarage();
	}

	@Override
	public IGarage getGarage(Integer id) throws NoSuchObjectException {
		return workPlaceService.getGarage(id);
	}

	@Override
	public void createWorkPlaceInGarage(int garageID) {
		workPlaceService.createWorkPlaceInGarage(garageID);
	}

	/**
	 * return false if collection had not been affected
	 * 
	 * @throws NoSuchObjectException
	 */
	@Override
	public void removeWorkPlaceInGarage(int garageID, int workPlaceID) throws NoSuchObjectException {
		workPlaceService.removeWorkPlaceInGarage(garageID, workPlaceID);
	}

	/** return first workplace with null order */
	@Override
	public WorkPlace findFreePlace() throws NoSuchObjectException {
		return workPlaceService.findFreePlace();
	}

	@Override
	public List<WorkPlace> getFreePlacesInDate(Date date) {
		return workPlaceService.getFreePlacesInDate(date);
	}

	/**
	 * return Order by ID
	 * 
	 * @throws NoSuchObjectException
	 *             - if no such mechanic was founded
	 */
	@Override
	public Ticket getOrder(int orderId) throws NoSuchObjectException {
		return orderService.getOrder(orderId);
	}

	/**
	 * @throws NoSuchObjectException
	 *             if collection was not affected
	 */
	@Override
	public void removeOrder(int order) throws NoSuchObjectException {
		orderService.removeOrder(order);
	}

	/** Rewrite dates to inputed days forward */
	@Override
	public void replaceDatesOfOrdersFrom(int order, int howManyDays) {
		orderService.replaceDatesOfOrdersFrom(order, howManyDays);
	}

	@Override
	public List<Ticket> getAllOrders() {
		return orderService.getAllOrders();
	}

	@Override
	public List<Ticket> getAllOrders(OrderStatus orderStatus) {
		return orderService.getAllOrders(orderStatus);
	}

	@Override
	public List<Ticket> sortOrdersByCompleteDateAction() {
		return orderService.sortOrdersByCompleteDateAction();
	}

	@Override
	public List<Ticket> sortOrdersByPriceAction() {
		return orderService.sortOrdersByPriceAction();
	}

	@Override
	public List<Ticket> sortOrdersByRequestDateAction() {
		return orderService.sortOrdersByRequestDateAction();
	}

	@Override
	public List<Ticket> sortOrdersByStartDateAction() {
		return orderService.sortOrdersByStartDateAction();
	}

	@Override
	public void importMechnaics() {
		mechanicService.importMechnaics();
	}

	@Override
	public void exportMechanics() {
		mechanicService.exportMechanics();
	}
	
	@Override
	public void addOrder(ITask task) throws NoSuchObjectException {
		
		Mechanic mechanic = null;
		WorkPlace workPlace = null;
		
		synchronized (mechanicService) {
			if ((mechanic = mechanicService.findFreeMechanic()) != null) {
				synchronized (workPlaceService) {
					if ((workPlace = workPlaceService.findFreePlace()) != null) {
						orderService.addOrder(task, mechanic, workPlace);
						return;
					}
				}
			} else {
				throw new NoSuchObjectException();
			}
		}
	}

}