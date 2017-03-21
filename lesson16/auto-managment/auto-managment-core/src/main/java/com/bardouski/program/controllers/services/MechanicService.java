package com.bardouski.program.controllers.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bardouski.config.ProgramProps;
import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.dbprocessor.IDBCSVProcessor;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.dto.MechanicDTO;
import com.bardouski.program.controllers.stores.dao.MechanicDAO;
import com.bardouski.program.utils.DBCSVProcessor;
import com.bardouski.propertiesholder.PropertiesContext;

public class MechanicService extends SessionReturnable implements IMechanicService {

	private MechanicDAO mechanicDAO;
	private DBCSVProcessor dbcsvProcessor;

	public MechanicService() throws ClassNotFoundException {
		mechanicDAO = (MechanicDAO) PropertiesContext.getInstance(MechanicDAO.class);
		dbcsvProcessor = (DBCSVProcessor) PropertiesContext.getInstance(IDBCSVProcessor.class);
		ProgramProps props = (ProgramProps) PropertiesContext.getInstance(ProgramProps.class);
		dbcsvProcessor.setPath(props.valueOf("dbCSVPath"));
	}

	@Override
	public void add(String fullName) {

		Session session = null;

		try {
			session = getSession();
			session.beginTransaction();
			mechanicDAO.create(session, new Mechanic(fullName));
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public MechanicDTO getMechanic(int id) {

		Session session = null;
		MechanicDTO dto = null;

		try {
			session = getSession();
			session.beginTransaction();
			Mechanic tempObject = mechanicDAO.getById(session, id);
			dto = new MechanicDTO(tempObject);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public void remove(int mechanicID) throws NoSuchObjectException {

		Session session = null;
		Mechanic tempObject = null;

		try {
			session = getSession();
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
	public List<MechanicDTO> getAllMechanics(String sortParameter) {

		Session session = null;
		List<MechanicDTO> dtoList = new ArrayList<>();

		try {
			List<Mechanic> tempList = null;
			session = getSession();
			session.beginTransaction();
				tempList = mechanicDAO.getAll(session, sortParameter);

			for (Mechanic mechanic : tempList) {
				dtoList.add(new MechanicDTO(mechanic));
			}
			session.getTransaction().commit();
			return dtoList;
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
		try {
			session = getSession();
			session.beginTransaction();
			Mechanic tempObject = mechanicDAO.findFreeMechanic(session);
			session.getTransaction().commit();
			return (tempObject);
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
			session = getSession();
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

		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			dbcsvProcessor.exportCSV(mechanicDAO.getAll(session));
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

}
