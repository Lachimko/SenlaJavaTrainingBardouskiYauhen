package bardouski.senla.training.facade;

import java.util.List;

import bardouski.senla.training.controllers.services.MechanicService;
import bardouski.senla.training.controllers.services.OrderService;
import bardouski.senla.training.controllers.services.WorkPlaceService;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
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
		// mechanicService = new MechanicService(dbProcessor.getMechanicsDB());
		// workPlaceService = new WorkPlaceService(dbProcessor.getGaragesDB());
		// orderService = new OrderService(dbProcessor.getOrdersDB());
	}
	/* END Constructors */

	public void addMechanic(String fullName) {
		mechanicService.add(fullName);
	}

	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicService.sortMechanicsByFullName();
	}

	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicService.sortMechanicsByCurrentWork();
	}

	public void removeMechanic(int id) {
		mechanicService.remove(id);
	}

	public void addGarage() {
		workPlaceService.addGarage();
	}

	public void addWorkPlaceInGarage(int garageId) {
		workPlaceService.addWorkPlaceInGarage(garageId);
	}

	public void removeWorkPlaceInGarage(int WorkPlaceId, int garageId) {
		workPlaceService.removeWorkPlaceInGarage(WorkPlaceId, garageId);
	}

	public void addOrder(String toDo, String startDateYYYY_MM_DD, String completeDateYYYY_MM_DD, double price) {
		
		Mechanic freeMechanic = mechanicService.findFreeMechanic();
		WorkPlace freeWorkPlace = workPlaceService.findFreePlace();
		
		orderService.createOrder(toDo, startDateYYYY_MM_DD, completeDateYYYY_MM_DD, price, freeMechanic, freeWorkPlace);
	}

	public void removeOrder(int orderId) {
		orderService.removeOrder(orderId);
	}

	public void closeOrder(int orderId) {
		orderService.closeOrder(orderId);
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

	public List<Order> viewOrdersInProcess() {
		return orderService.ordersInProcess();
	}

	public List<Order> viewOrdersInProcessSortedByRequestDate() {
		return orderService.viewOrdersInProcessSortedByRequestDate();
	}

	public List<Order> viewOrdersInProcessSortedByCompleteDate() {
		return orderService.viewOrdersInProcessSortedByCompleteDate();
	}

	public List<Order> viewOrdersInProcessSortedByPrice() {
		return orderService.viewOrdersInProcessSortedByPrice();
	}

	public Mechanic returnMechanicOfOrder(int orderId) {
		return orderService.returnMechanicOfOrder(orderId);
	}

	public Order returnOrderOfMechanic(int mechanicId) {
		return mechanicService.returnOrderOfMechanic(mechanicId);
	}

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
