package com.bardouski.program.controllers.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bardouski.config.ProgramProps;
import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.dbprocessor.IDBCSVProcessor;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.hibernate.initializer.HibernateUtil;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.program.controllers.stores.dao.MechanicDAO;
import com.bardouski.program.dbprocessor.DBCSVProcessor;
import com.bardouski.propertiesholder.PropertiesContext;

public class MechanicService implements IMechanicService {

	private HibernateUtil hibernateUtil;
	private MechanicDAO mechanicDAO;
	private DBCSVProcessor dbcsvProcessor;

	public MechanicService() throws ClassNotFoundException {
		mechanicDAO = (MechanicDAO) PropertiesContext.getInstance(MechanicDAO.class);
		hibernateUtil = (HibernateUtil) PropertiesContext.getInstance(HibernateUtil.class);
		HibernateUtil.getInstance();
		dbcsvProcessor = (DBCSVProcessor) PropertiesContext.getInstance(IDBCSVProcessor.class);
		dbcsvProcessor.setPath(ProgramProps.valueOf("dbCSVPath"));
	}

	@Override
	public void add(String fullName) {

		Session session = null;
		Mechanic tempObject = new Mechanic(fullName);

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			mechanicDAO.create(session, tempObject);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public Mechanic getMechanic(int id) {

		Session session = null;
		Mechanic tempObject = null;

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempObject = mechanicDAO.getById(session, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return tempObject;
	}

	@Override
	public void remove(int mechanicID) throws NoSuchObjectException {

		Session session = null;
		Mechanic tempObject = null;

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempObject = mechanicDAO.getProxyById(session, new Integer(mechanicID));
			mechanicDAO.delete(session, tempObject);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

	}

	@Override
	public List<Mechanic> getAllMechanics() {

		Session session = null;
		List<Mechanic> tempList = null;

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempList = mechanicDAO.getAll(session);
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
	public List<Mechanic> sortMechanicsByFullName() {

		Session session = null;
		List<Mechanic> tempList = null;

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempList = mechanicDAO.sortMechanicsByFullName(session);
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
	public List<Mechanic> sortMechanicsByCurrentWork() {

		Session session = null;
		List<Mechanic> tempList = null;

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempList = mechanicDAO.sortMechanicsByWork(session);
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
	public Mechanic findFreeMechanic() throws NoSuchObjectException {

		Session session = null;
		Mechanic tempObject = null;
		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempObject = mechanicDAO.findFreeMechanic(session);
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
	public void importMechnaics() {
		List<Mechanic> imported = dbcsvProcessor.importCollection();

		Session session = null;
		try {
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			for (Mechanic mechanic : imported) {
				mechanicDAO.saveOrUpdate(session, mechanic);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public void exportMechanics() {
		dbcsvProcessor.exportCSV(getAllMechanics());
	}

}
