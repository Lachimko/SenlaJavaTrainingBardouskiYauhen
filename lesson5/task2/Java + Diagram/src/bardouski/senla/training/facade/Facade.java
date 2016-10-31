package bardouski.senla.training.facade;

import java.text.ParseException;
import java.util.List;

import bardouski.senla.training.controllers.services.MechanicService;
import bardouski.senla.training.controllers.services.OrderService;
import bardouski.senla.training.controllers.services.WorkPlaceService;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.EmptyCollectionException;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;
import bardouski.senla.training.model.WorkPlace;

public class Facade {

	private MechanicService mechanicService;
	private WorkPlaceService workPlaceService;
	private OrderService orderService;

	/* Constructors */
	public Facade(String DbFileName) {

		DBProcessor dbProcessor = new DBProcessor(DbFileName);

		mechanicService = new MechanicService(dbProcessor);
		workPlaceService = new WorkPlaceService(dbProcessor);
		orderService = new OrderService(dbProcessor);
	}
	/* END Constructors */

	public void addMechanic(Mechanic mechanic) {
		mechanicService.add(mechanic);
	}
	
	public Mechanic getMechanic(int id) throws NoSuchObjectException{
		return mechanicService.getMechanic(id);
	}
	
	public Garage getGarage(int id) throws NoSuchObjectException{
		return workPlaceService.getGarage(id);
	}
	
	public void addWorkPlaceInGarage(Garage garage) {
		workPlaceService.addWorkPlaceInGarage(garage);
	}
	
	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicService.sortMechanicsByFullName();
	}

	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicService.sortMechanicsByCurrentWork();
	}

	public Mechanic findFreeMechanic(){
		return mechanicService.findFreeMechanic();
	}

	public WorkPlace findFreePlace(){
		return workPlaceService.findFreePlace();
	}
	
	public Task createTask(String toDo, String startDate, String completeDate, double price) throws ParseException{
		return orderService.createTask(toDo, startDate, completeDate, price);
	}
	
	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderService.getOrder(orderId);
	}

	
	
	
	public void removeMechanic(Mechanic mechanic) {
		mechanicService.remove(mechanic);
	}

	public void addGarage(Garage garage) {
		workPlaceService.addGarage(garage);
	}
	




	public boolean removeWorkPlaceInGarage(int WorkPlaceId, int garageId) throws EmptyCollectionException {
		return workPlaceService.removeWorkPlaceInGarage(WorkPlaceId, garageId);
	}

	/**
	 * @param startDate - pattern to input YYYY-MM-DD
	 * @param completeDate -  - pattern to input YYYY-MM-DD
	 */
	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	public void removeOrder(Order order) {
		orderService.removeOrder(order);
	}

	public void cancelOrder(int orderId) {
		orderService.cancelOrder(orderId);
	}


	
	public void replaceDatesOfOrdersFrom(int delayedOrderId, int howManyDays) {
		orderService.replaceDatesOfOrdersFrom(delayedOrderId, howManyDays);
	}

	public List<Order> sortOrderByRequestDate() {
		return orderService.sortOrderByRequestDate();
	}

	public List<Order> sortOrderByCompleteDate() {
		return orderService.sortOrderByCompleteDate();
	}

	public List<Order> sortOrderByStartDate() {
		return orderService.sortOrderByStartDate();
	}

	public List<Order> sortOrderByPrice() {
		return orderService.sortOrderByPrice();
	}

	public List<Order> viewOrdersInProcess(List<Order> orders) {
		return orderService.ordersInProcess(orders);
	}

	public List<Order> viewOrdersDeleted(List<Order> orders){
		return orderService.ordersDeleted(orders);
	}
	
	public List<Order> viewOrdersCompleted(List<Order> orders){
		return orderService.ordersCompleted(orders);
	}
	
	public List<Order> viewOrdersCancelled(List<Order> orders){
		return orderService.ordersCancelled(orders);
	}
	
//	public List<Order> viewOrdersInProcessSortedByRequestDate() {
//		return orderService.viewOrdersInProcessSortedByRequestDate();
//	}

//	public List<Order> viewOrdersInProcessSortedByCompleteDate() {
//		return orderService.viewOrdersInProcessSortedByCompleteDate();
//	}
//
//	public List<Order> viewOrdersInProcessSortedByPrice() {
//		return orderService.viewOrdersInProcessSortedByPrice();
//	}

	public Mechanic returnMechanicOfOrder(int orderId) {
		return orderService.returnMechanicOfOrder(orderId);
	}

//	public Order returnOrderOfMechanic(int mechanicId) throws NoSuchObjectException {
//		return mechanicService.returnOrderOfMechanic(mechanicId);
//	}

	//// Orders from Date to Date
	public List<Order> returnOrdersBetweenRequestDate(String fromDateYYYY_MM_DD, String toDateYYYY_MM_DD) {
		return orderService.returnOrdersBetweenRequestDate(fromDateYYYY_MM_DD, toDateYYYY_MM_DD);
	}

	public List<Order> returnOrdersBetweenCompleteDate(String fromDateYYYY_MM_DD, String toDateYYYY_MM_DD) {
		return orderService.returnOrdersBetweenCompleteDate(fromDateYYYY_MM_DD, toDateYYYY_MM_DD);
	}

	//// READY, DELETED, CANCELLED Orders from Date to Date
	public List<Order> returnCompleteOrdersBetweenCompleteDate(String fromDateYYYY_MM_DD, String toDateYYYY_MM_DD) {
		return orderService.returnCompleteOrdersBetweenCompleteDate(fromDateYYYY_MM_DD, toDateYYYY_MM_DD);
	}


}
