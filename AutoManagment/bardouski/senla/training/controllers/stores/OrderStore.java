package bardouski.senla.training.controllers.stores;

import bardouski.senla.training.controllers.managers.PersonalManager;
import bardouski.senla.training.controllers.managers.WorkPlaceManager;
import bardouski.senla.training.controllers.stores.abstracts.AbstractStore;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;

public class OrderStore extends AbstractStore<Order>{

//	@Override
//	public void create(Order obj) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void read(Order obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Order obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String serialize(Order object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String returnStringFromLocalDB(Order object) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public Order deserialize(String orderString){
//		
//		final String id_pattern = "id:";
//		final String mechanic_pattern = "mechanic:";
//		final String workPlace_pattern = "workPlace:";
//		final String task_pattern = "task:";
//		final String orderStatus_pattern = "orderStatus:";
//		final String fieldEnd_pattern = ";";
//		
//		int orderId;
//		int startFieldPosition;
//		int endFieldPosition;
//		
//		String orderMechanicString;
//		String orderWorkplaceString;
//		String orderTaskString;
//		String orderStatusString;
//		
//		Mechanic orderMechanic = null;
//		WorkPlace orderWorkplace = null;
//		Task orderTask = null;
//		OrderStatus orderStatus = null;
//		
//		//order ID parse
//		startFieldPosition = orderString.indexOf(id_pattern) + id_pattern.length();
//		endFieldPosition = orderString.indexOf(fieldEnd_pattern, startFieldPosition);
//		orderId = Integer.parseInt((orderString.substring(startFieldPosition, endFieldPosition)));
//		
//		//Order MECHANIC parse
//		startFieldPosition = orderString.indexOf(mechanic_pattern) + mechanic_pattern.length();
//		endFieldPosition = orderString.indexOf(fieldEnd_pattern, startFieldPosition);
//		orderMechanicString = orderString.substring(startFieldPosition, endFieldPosition);
//		orderMechanic = PersonalManager.getInstance().getPersonalStore().deserialize(orderMechanicString);
//		
//		System.out.println("ID:" + orderId);
//		System.out.println("Mechanic:" + orderMechanic);
//		return null;
//	}

}
