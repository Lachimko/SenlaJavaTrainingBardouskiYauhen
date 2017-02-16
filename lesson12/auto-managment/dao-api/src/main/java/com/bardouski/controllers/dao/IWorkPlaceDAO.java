package com.bardouski.controllers.dao;

import java.sql.Connection;

import com.bardouski.model.impl.WorkPlace;

public interface IWorkPlaceDAO {

	void create(Connection connection, int garageID);

	WorkPlace getFreePlace(Connection connection) throws Exception;

	boolean removeWorkPlaceInGarage(Connection connection, int garageID, int placeID) throws Exception;

	
}
