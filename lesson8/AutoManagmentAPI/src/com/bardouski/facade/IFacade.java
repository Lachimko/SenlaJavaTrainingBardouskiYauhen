package com.bardouski.facade;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.*;
import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IFacade {

	/** Add mechanic to MechanicStore collection */
	void addMechanic(IMechanic mechanic);

	/** Add new mechanic with @param fullName to MechanicStore collection */
	void addMechanic(String fullName);
	
	/**
	 * Remove mechanic from MechanicStore collection
	 * 
	 * @throws Exception
	 *             - if collection was not affected
	 */
	void removeMechanic(IMechanic mechanic) throws NoSuchObjectException;

	/**
	 * Return mechanic from MechanicStore collection by inputed id
	 * 
	 * @throws Exception
	 *             - if no such object with inputed id in collection
	 */
	IMechanic getMechanic(int id) throws NoSuchObjectException;

	/** Return collection of all mechanics from MechanicStore collection */
	List<? extends IMechanic> getAllMechanics();

	/**
	 * Return sorted collection of all mechanics from MechanicStore collection
	 */
	List<? extends IMechanic> sortMechanicsByFullName();

	/**
	 * Return sorted collection of all mechanics from MechanicStore collection
	 */
	List<? extends IMechanic> sortMechanicsByCurrentWork();

	/** Return mechanic with null currentOrder field */
	IMechanic findFreeMechanic();

	/** Add garage to WorkPlaceStore collection */
	void addGarage(IGarage garage);
	
	/** Add new garage to WorkPlaceStore collection */
	void addGarage();

	/**
	 * Return Garage from WorkPlaceStore collection by inputed id
	 * 
	 * @throws Exception
	 *             - if no such object with inputed id in collection
	 */
	IGarage getGarage(Integer id) throws NoSuchObjectException;

	/** Create a WorkPlace in Garage, send in parameter garage */
	void addWorkPlaceInGarage(IGarage garage);

	/** return false if collection had not been affected */
	boolean removeWorkPlaceInGarage(IGarage garage, int workPlaceId);

	/** return first WorkPlace with null order */
	IWorkPlace findFreePlace() throws EmptyCollectionException;

	/** Return WorkPlace with null Order field */
	List<? extends IWorkPlace> getFreePlacesInDate(Date date);

	/**
	 * Get order from collection by Id
	 * 
	 * @throws Exception
	 *             - if no such object was in collection
	 */
	IOrder getOrder(int orderId) throws NoSuchObjectException;

	/** Return new unique Id from OrderStore. Use for getting clone */
	int getNextOrderId();

	/** Add Order to collection */
	void addOrder(IOrder order);

	/**
	 * Remove order from OrderStore collection.
	 * 
	 * @throws Exception
	 *             - if collection was not affected
	 */
	void removeOrder(IOrder order) throws NoSuchObjectException;

	/** Rewrite dates to inputed days forward */
	void replaceDatesOfOrdersFrom(IOrder order, int howManyDays);

	/** Return collection of all orders */
	List<? extends IOrder> getAllOrders();

	/**
	 * Get all orders with status field are equals orderStatus from parameters
	 */
	List<? extends IOrder> getAllOrders(OrderStatus orderStatus);

	/**
	 * return collection of orders sorted by transfer in parameter comparator
	 */
	List<? extends IOrder> returnOrdersSortedByComparator(Comparator<IOrder> comparator);

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
