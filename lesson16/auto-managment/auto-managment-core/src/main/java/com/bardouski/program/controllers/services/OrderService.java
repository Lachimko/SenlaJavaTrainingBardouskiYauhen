package com.bardouski.program.controllers.services;

import java.util.ArrayList;
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
import com.bardouski.model.impl.dto.TicketDTO;
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
	public TicketDTO getOrder(int orderId) throws NoSuchObjectException {

		Session session = null;
		TicketDTO dto = null;

		try {
			session = getSession();
			session.beginTransaction();
			Ticket tempObject = orderDAO.getById(session, new Integer(orderId));
			dto = new TicketDTO(tempObject);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return dto;

		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public List<TicketDTO> getAllOrders(String sortParameter) {

		Session session = null;
		List<TicketDTO> daoList = new ArrayList<>();

		try {
			session = getSession();
			session.beginTransaction();
			List<Ticket> tempList = orderDAO.getAll(session, sortParameter);
			for (Ticket ticket : tempList) {
				daoList.add(new TicketDTO(ticket));
			}
			session.getTransaction().commit();
			return daoList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return daoList;

	}

	@Override
	public List<TicketDTO> getAllOrders(OrderStatus orderStatus) {

		Session session = null;
		List<TicketDTO> daoList = new ArrayList<>();

		try {
			session = getSession();
			session.beginTransaction();
			List<Ticket> tempList = orderDAO.getAll(session, orderStatus);
			for (Ticket ticket : tempList) {
				daoList.add(new TicketDTO(ticket));
			}
			session.getTransaction().commit();
			return daoList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return daoList;
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
