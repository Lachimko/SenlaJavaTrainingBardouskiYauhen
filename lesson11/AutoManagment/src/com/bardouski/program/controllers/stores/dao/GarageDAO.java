package com.bardouski.program.controllers.stores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bardouski.model.impl.Garage;

public class GarageDAO extends AbstractDAO<Garage> {

	private static final String ADD_GARAGE = "insert into garage value (?)";

	private static final String SELECT_ALL_FROM_GARAGE = "select * from garage";
	private static final String SELECT_GARAGE_BY_ID = "select * from garage where id_garage = ?";
	private static final String DELETE_GARAGE_BY_ID = "delete from garage where id_garage = ?";

	public String injectGetByIdQuery() {
		return SELECT_GARAGE_BY_ID;
	}

	public Garage parseEntity(ResultSet resultSet) {

		try {
			Garage temp = new Garage();
			temp.setId(resultSet.getInt("id_garage"));
			return temp;

		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public String injectInsertQuery() {
		return ADD_GARAGE;
	}

	@Override
	public String injectDeleteQuery() {
		return DELETE_GARAGE_BY_ID;
	}

	@Override
	public String injectGetAllQuery() {
		return SELECT_ALL_FROM_GARAGE;
	}

	@Override
	public String injectUpdateQuery() {
		return null;
	}

	@Override
	public void prepareUpdateStatement(PreparedStatement statement, Garage object) throws SQLException {
		
	}

	@Override
	public void prepareInsertStatement(PreparedStatement statement, Garage object) throws SQLException {
		statement.setString(1, null);
	}

}
