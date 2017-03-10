package com.bardouski.program.controllers.services;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.program.controllers.stores.dao.GarageDAO;
import com.bardouski.program.controllers.stores.dao.WorkPlaceDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class WorkPlaceService extends SessionReturnable implements IWorkPlaceService {

	private WorkPlaceDAO workPlaceDAO;
	private GarageDAO garageDAO;

	public WorkPlaceService() throws ClassNotFoundException {
		workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
		garageDAO = (GarageDAO) PropertiesContext.getInstance(GarageDAO.class);
	}

	@Override
	public void addGarage() {

		Session session = null;
		Garage tempObject = new Garage();

		try {
			session = getSession();
			session.beginTransaction();
			garageDAO.create(session, tempObject);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public List<Garage> getGarages() {

		Session session = null;

		try {
			session = getSession();
			return garageDAO.getAll(session);
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;

		} finally {
			session.close();
		}
	}

	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {

		Session session = null;

		try {
			session = getSession();
			return garageDAO.getById(session, id);
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;

		} finally {
			session.close();
		}
	}

	@Override
	public void createWorkPlaceInGarage(int garageID) {

		Session session = null;
		WorkPlace tempWorkPlace = new WorkPlace();

		try {
			session = getSession();
			session.persist(tempWorkPlace);
			session.flush();
			session.beginTransaction();
			Garage tempGarage = garageDAO.getById(session, new Integer(garageID));
			tempGarage.getWorkPlaces().add(tempWorkPlace);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public WorkPlace findFreePlace() throws NoSuchObjectException {

		Session session = null;
		WorkPlace tempObject = null;

		try {
			session = getSession();
			session.beginTransaction();
			tempObject = workPlaceDAO.getFreePlace(session);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return tempObject;
	}

	@Override
	public void removeWorkPlaceInGarage(int garageID, int placeID) throws NoSuchObjectException {

		Session session = null;

		try {
			session = getSession();
			Garage tempGarage = garageDAO.getById(session, new Integer(garageID));
			session.beginTransaction();
			workPlaceDAO.removeWorkPlaceInGarage(session, tempGarage, placeID);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

		} finally {
			session.close();
		}
	}

	@Override
	public List<WorkPlace> getFreePlacesInDate(Date date) {

		Session session = null;
		List<WorkPlace> tempList = null;
		
		try {
			
			session = getSession();
			session.beginTransaction();
			tempList = workPlaceDAO.getFreePlacesInDate(session, date);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;

		} finally {
			session.close();
		}
		
	}

}
