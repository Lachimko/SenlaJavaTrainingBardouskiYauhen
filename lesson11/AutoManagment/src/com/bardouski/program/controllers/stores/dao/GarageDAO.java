package com.bardouski.program.controllers.stores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IGarageDAO;
import com.bardouski.model.impl.Garage;

public class GarageDAO extends AbstractDAO<Garage> implements IGarageDAO {

	private static final String ID_GARAGE = "id_garage";
	private static final String ADD_GARAGE = "insert into garage value (?)";
	private static final String SELECT_ALL_FROM_GARAGE = "select * from garage";
	private static final String SELECT_GARAGE_BY_ID = "select * from garage where id_garage = ?";
	private static final String DELETE_GARAGE_BY_ID = "delete from garage where id_garage = ?";

	protected String injectGetByIdQuery() {
		return SELECT_GARAGE_BY_ID;
	}

	@Override
	protected String injectInsertQuery() {
		return ADD_GARAGE;
	}

	@Override
	protected String injectDeleteQuery() {
		return DELETE_GARAGE_BY_ID;
	}

	@Override
	protected String injectGetAllQuery() {
		return SELECT_ALL_FROM_GARAGE;
	}

	@Override
	protected String injectUpdateQuery() {
		return null;
	}

	protected Garage parseEntity(ResultSet resultSet) {
		try {
			Garage temp = new Garage();
			temp.setId(resultSet.getInt(ID_GARAGE));
			return temp;

		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	protected void prepareUpdateStatement(PreparedStatement statement, Garage object) throws SQLException {
		return; // No fields to update
	}

	@Override
	protected void prepareInsertStatement(PreparedStatement statement, Garage object) throws SQLException {
		statement.setString(1, null);
	}

}
