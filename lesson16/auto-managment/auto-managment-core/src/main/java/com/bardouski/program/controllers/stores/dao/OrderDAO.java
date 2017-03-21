package com.bardouski.program.controllers.stores.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IOrderDAO;
import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.enums.OrderStatus;

public class OrderDAO extends AbstractDAO<Ticket> implements IOrderDAO {

	private static final String ORDER_STATUS = "orderStatus";

	public OrderDAO() {
		super(Ticket.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getAll(Session session, OrderStatus orderStatus) {
		return session.createCriteria(returnClass()).add(Restrictions.eq(ORDER_STATUS, orderStatus.toString())).list();
	}

	public void updateAllReferences(Session session, Ticket ticket) {
		session.persist(ticket);
		session.update(ticket.getMechanic());
		session.update(ticket.getWorkPlace());
	}

}
