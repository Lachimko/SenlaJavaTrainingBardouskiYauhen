package com.bardouski.program.controllers.stores.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IOrderDAO;
import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderDAO extends AbstractDAO<Ticket> implements IOrderDAO {

	private static final String START_DATE = "start_date";
	private static final String REQUEST_DATE = "request_date";
	private static final String PRICE = "price";
	private static final String COMPLETE_DATE = "complete_date";

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

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkPlace> getFreePlacesInDate(Session session, Date date) {

		WorkPlaceDAO workPlaceDAO = null;
		try {
			workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
		} catch (ClassNotFoundException e1) {
			logger.error(e1.getMessage());
		}
		
		return session.createCriteria(workPlaceDAO.getClass()).add(Restrictions.eq(COMPLETE_DATE, date)).list();
	}

	@Override
	protected Class<Ticket> returnClass() {
		return Ticket.class;
	}

}
