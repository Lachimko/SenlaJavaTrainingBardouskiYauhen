package com.bardouski.program.controllers.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.hibernate.initializer.HibernateUtil;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.program.controllers.stores.dao.GarageDAO;
import com.bardouski.program.controllers.stores.dao.WorkPlaceDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class WorkPlaceService implements IWorkPlaceService {

	private HibernateUtil hibernateUtil;
	private WorkPlaceDAO workPlaceDAO;
	private GarageDAO garageDAO;

	public WorkPlaceService() throws ClassNotFoundException {
		workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
		garageDAO = (GarageDAO) PropertiesContext.getInstance(GarageDAO.class);
		hibernateUtil = (HibernateUtil) PropertiesContext.getInstance(HibernateUtil.class);
		HibernateUtil.getInstance();
	}

	@Override
	public void addGarage() {
		
		Session session = null;
		Garage tempObject = new Garage();
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			garageDAO.create(session, tempObject);
			session.getTransaction().commit();
		} catch (HibernateException e){
			session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
	}

	@Override
	public List<Garage> getGarages() {
		
		Session session = null;
		List<Garage> tempList = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			garageDAO.getAll(session);
			session.getTransaction().commit();
			return tempList;
		} catch (HibernateException e){
			session.getTransaction().rollback();
			return null;
			
		} finally {
			session.close();
		}
	}

	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {
		
		Session session = null;
		Garage tempObject = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempObject = garageDAO.getById(session, id);
			session.getTransaction().commit();
		} catch (HibernateException e){
			session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
		
		return tempObject;
	}

	@Override
	public void createWorkPlaceInGarage(int garageID) {
		
		Session session = null;
		Garage tempGarage = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempGarage = garageDAO.getById(session, new Integer(garageID));
			tempGarage.getWorkPlaces().add(new WorkPlace());
			session.getTransaction().commit();
		} catch (HibernateException e){
			session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
	}
	
	@Override
	public WorkPlace findFreePlace() throws NoSuchObjectException {
		
		Session session = null;
		WorkPlace tempObject = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tempObject = workPlaceDAO.getFreePlace(session);
			session.getTransaction().commit();
		} catch (HibernateException e){
			session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
		
		return tempObject;
	}

	@Override
	public void removeWorkPlaceInGarage(int garageID, int placeID) throws NoSuchObjectException {
		
		Session session = null;
		Garage tempGarage = null;
		
		try{
			session = hibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			WorkPlace tempObject = workPlaceDAO.getById(session, new Integer(placeID));
			tempGarage = tempObject.getGarage();
			workPlaceDAO.delete(session, tempObject);
			session.update(tempGarage);
			session.getTransaction().commit();
		} catch (HibernateException e){
			session.getTransaction().rollback();
			
		} finally {
			session.close();
		}
	}

}
