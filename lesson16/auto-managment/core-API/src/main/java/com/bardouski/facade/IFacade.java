package com.bardouski.facade;

import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.controllers.services.IOrderService;
import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IMechanic;
import com.bardouski.model.ITask;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.dto.MechanicDTO;
import com.bardouski.model.impl.dto.TicketDTO;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IFacade {

	/** Mechanic's service */
	
	void createMechanic(String fullName);

	void removeMechanic(int mechanicID) throws NoSuchObjectException;

	MechanicDTO getMechanic(int id) throws NoSuchObjectException;

	List<MechanicDTO> getAllMechanics(String sortParameter);

	IMechanic findFreeMechanic() throws NoSuchObjectException;

	void importMechnaics();

	void exportMechanics();

	/** Workplace's service */
	void createGarage();

	IGarage getGarage(Integer id) throws NoSuchObjectException;

	void createWorkPlaceInGarage(int garageID);

	void removeWorkPlaceInGarage(int garageID, int workPlaceID) throws NoSuchObjectException;

	IWorkPlace findFreePlace() throws NoSuchObjectException;

	List<? extends IWorkPlace> getFreePlacesInDate(Date date);

	/** Order's service */
	
	TicketDTO getOrder(int orderId) throws NoSuchObjectException;

	void addOrder(ITask task) throws NoSuchObjectException;

	void removeOrder(int order) throws NoSuchObjectException;

	void replaceDatesOfOrdersFrom(int order, int howManyDays);

	List<TicketDTO> getAllOrders(String sortParameter);

	List<TicketDTO> getAllOrders(OrderStatus orderStatus);

	void setMechanicService(IMechanicService mechanicService);

	void setWorkPlaceService(IWorkPlaceService workPlaceService);

	void setOrderService(IOrderService orderService);

}
