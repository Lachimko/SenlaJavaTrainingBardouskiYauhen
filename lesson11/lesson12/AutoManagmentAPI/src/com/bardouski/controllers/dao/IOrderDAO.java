package com.bardouski.controllers.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrderDAO {

	List<Ticket> getAll(Session session, OrderStatus orderStatus);

	List<Ticket> sortOrdersByCompleteDateAction(Session session);

	List<Ticket> sortOrdersByPriceAction(Session session);

	List<Ticket> sortOrdersByRequestDateAction(Session session);

	List<Ticket> sortOrdersByStartDateAction(Session session);

	List<WorkPlace> getFreePlacesInDate(Session session, Date date);
}
