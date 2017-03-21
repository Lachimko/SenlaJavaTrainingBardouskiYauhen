package com.bardouski.controllers.services;

import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.dto.TicketDTO;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrderService {

	void addOrder(ITask task, Mechanic mechanic, WorkPlace workPlace);
	
	void addOrder(IOrder order);

	TicketDTO getOrder(int orderId) throws NoSuchObjectException;

	List<TicketDTO> getAllOrders(String sortParameter);
	
	List<TicketDTO> getAllOrders(OrderStatus orderStatus);
	
	void replaceDatesOfOrdersFrom(int order, int howManyDays);

	void removeOrder(int order) throws NoSuchObjectException;
	
}
