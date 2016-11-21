package com.bardouski.program.facade;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.program.controllers.services.IMechanicService;
import com.bardouski.program.controllers.services.IOrderService;
import com.bardouski.program.controllers.services.IWorkPlaceService;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.program.model.enums.OrderStatus;

public interface IFacade {

	/** Add mechanic to MechanicStore collection */
	void addMechanic(Mechanic mechanic);

	/**
	 * Remove mechanic from MechanicStore collection
	 * 
	 * @throws Exception
	 *             - if collection was not affected
	 */
	void removeMechanic(Mechanic mechanic) throws NoSuchObjectException;

	/**
	 * Return mechanic from MechanicStore collection by inputed id
	 * 
	 * @throws Exception
	 *             - if no such object with inputed id in collection
	 */
	Mechanic getMechanic(int id) throws Exception;

	/** Return collection of all mechanics from MechanicStore collection */
	List<Mechanic> getAllMechanics();

	/**
	 * Return sorted collection of all mechanics from MechanicStore collection
	 */
	List<Mechanic> sortMechanicsByFullName();

	/**
	 * Return sorted collection of all mechanics from MechanicStore collection
	 */
	List<Mechanic> sortMechanicsByCurrentWork();

	/** Return mechanic with null currentOrder field */
	Mechanic findFreeMechanic();

	/** Add garage to WorkPlaceStore collection */
	void addGarage(Garage garage);

	/**
	 * Return Garage from WorkPlaceStore collection by inputed id
	 * 
	 * @throws Exception
	 *             - if no such object with inputed id in collection
	 */
	Garage getGarage(int id) throws Exception;

	/** Create a WorkPlace in Garage, send in parameter garage */
	void addWorkPlaceInGarage(Garage garage);

	/** return false if collection had not been affected */
	boolean removeWorkPlaceInGarage(Garage garage, int workPlaceId);

	/** return first WorkPlace with null order */
	WorkPlace findFreePlace() throws Exception;

	/** Return WorkPlace with null Order field */
	List<WorkPlace> getFreePlacesInDate(Date date);

	/**
	 * Get order from collection by Id
	 * 
	 * @throws Exception
	 *             - if no such object was in collection
	 */
	Order getOrder(int orderId) throws Exception;

	/** Return new unique Id from OrderStore. Use for getting clone */
	int getNextOrderId();

	/** Add Order to collection */
	void addOrder(Order order);

	/**
	 * Remove order from OrderStore collection.
	 * 
	 * @throws Exception
	 *             - if collection was not affected
	 */
	void removeOrder(Order order) throws Exception;

	/** Rewrite dates to inputed days forward */
	void replaceDatesOfOrdersFrom(Order order, int howManyDays);

	/** Return collection of all orders */
	List<Order> getAllOrders();

	/**
	 * Get all orders with status field are equals orderStatus from parameters
	 */
	List<Order> getAllOrders(OrderStatus orderStatus);

	/**
	 * return collection of orders sorted by transfer in parameter comparator
	 */
	List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator);

	/** Save to DB all Stores collections */
	void saveToFile();

	/** Import mechanics list to CSV file */
	void importMechnaics();

	/** Export mechanic collection into program */
	void exportMechanics();
	
	void setMechanicService(IMechanicService mechanicService);

	void setWorkPlaceService(IWorkPlaceService workPlaceService);

	void setOrderService(IOrderService orderService);

}
