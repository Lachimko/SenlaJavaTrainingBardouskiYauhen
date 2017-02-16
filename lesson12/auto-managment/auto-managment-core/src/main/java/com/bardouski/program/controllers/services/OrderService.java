package com.bardouski.program.controllers.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bardouski.controllers.services.IOrderService;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.program.controllers.stores.dao.OrderDAO;
import com.bardouski.program.controllers.stores.dao.TaskDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderService extends SessionReturnable implements IOrderService {

	private OrderDAO orderDAO;
	@SuppressWarnings("unused")
	private TaskDAO taskDAO;

	public OrderService() throws ClassNotFoundException {
		orderDAO = (OrderDAO) PropertiesContext.getInstance(OrderDAO.class);
		taskDAO = (TaskDAO) PropertiesContext.getInstance(TaskDAO.class);
	}

	/** get Instances from DI pool than call methods in transaction scope */
	@Override
	public void addOrder(IOrder order) {

		Session session = null;

		try {
			session = getSession();
			session.beginTransaction();
			orderDAO.create(session, (Ticket) order);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public Ticket getOrder(int orderId) throws NoSuchObjectException {

		Session session = null;
		Ticket tempObject = null;

		try {
			session = getSession();
			session.beginTransaction();
			orderDAO.getById(session, new Integer(orderId));
			session.getTransaction().commit();
			return tempObject;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;

		} finally {
			session.close();
		}
	}

	@Override
	public List<Ticket> getAllOrders() {

		Session session = null;
		List<Ticket> tempList = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempList = orderDAO.getAll(session);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public List<Ticket> getAllOrders(OrderStatus orderStatus) {

		Session session = null;
		List<Ticket> tempList = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempList = orderDAO.getAll(session, orderStatus);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public void removeOrder(int orderID) throws NoSuchObjectException {

		Session session = null;

		try {
			session = getSession();
			session.beginTransaction();
			orderDAO.delete(session, orderDAO.getById(session, new Integer(orderID)));
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	public List<Ticket> sortOrdersByCompleteDateAction() {

		Session session = null;
		List<Ticket> tempList = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempList = orderDAO.sortOrdersByCompleteDateAction(session);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Ticket> sortOrdersByPriceAction() {

		Session session = null;
		List<Ticket> tempList = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempList = orderDAO.sortOrdersByPriceAction(session);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Ticket> sortOrdersByRequestDateAction() {

		Session session = null;
		List<Ticket> tempList = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempList = orderDAO.sortOrdersByRequestDateAction(session);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Ticket> sortOrdersByStartDateAction() {

		Session session = null;
		List<Ticket> tempList = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempList = orderDAO.sortOrdersByStartDateAction(session);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public void replaceDatesOfOrdersFrom(int order, int howManyDays) {

		Session session = null;
		Ticket ticket = orderDAO.getById(session, new Integer(order));

		try {
			session = getSession();
			session.beginTransaction();
			ticket.getTask().setCompleteDate(ticket.getTask().getCompleteDate());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void addOrder(ITask task, Mechanic mechanic, WorkPlace workPlace) {

		Session session = null;
		Ticket ticket = new Ticket();

		try {
			session = getSession();
			session.beginTransaction();
			ticket.setTask(task);
			ticket.setMechanic(mechanic);
			mechanic.setCurrentOrder(ticket);
			ticket.setWorkPlace(workPlace);
			workPlace.setOrder(ticket);
			orderDAO.updateAllReferences(session, ticket);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

}
