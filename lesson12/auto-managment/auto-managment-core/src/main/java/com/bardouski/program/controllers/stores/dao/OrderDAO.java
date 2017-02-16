package com.bardouski.program.controllers.stores.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IOrderDAO;
import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.enums.OrderStatus;

public class OrderDAO extends AbstractDAO<Ticket> implements IOrderDAO {

	private static final String START_DATE = "start_date";
	private static final String REQUEST_DATE = "request_date";
	private static final String PRICE = "price";
	private static final String COMPLETE_DATE = "complete_date";

	public OrderDAO() {
		super(Ticket.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAll(Session session, OrderStatus orderStatus) {
		return session.createCriteria(returnClass()).add(Restrictions.eq("orderStatus", orderStatus.toString())).list();
	}

	@Override
	public List<Ticket> sortOrdersByCompleteDateAction(Session session) {
		return getAll(session, COMPLETE_DATE);
	}

	@Override
	public List<Ticket> sortOrdersByPriceAction(Session session) {
		return getAll(session, PRICE);
	}

	@Override
	public List<Ticket> sortOrdersByRequestDateAction(Session session) {
		return getAll(session, REQUEST_DATE);
	}

	@Override
	public List<Ticket> sortOrdersByStartDateAction(Session session) {
		return getAll(session, START_DATE);
	}

	public void updateAllReferences(Session session, Ticket ticket) {
		session.persist(ticket);
		session.update(ticket.getMechanic());
		session.update(ticket.getWorkPlace());
	}

}
