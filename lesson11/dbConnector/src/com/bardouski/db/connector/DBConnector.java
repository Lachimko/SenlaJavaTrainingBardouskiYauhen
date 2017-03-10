package com.bardouski.db.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

/**
 * Class connector to mysql database. Need to be configured first by DB
 * connection parameters.
 */
public final class DBConnector {

	private static DBConnector instance = null;
	private Connection connection = null;

	/** Configure and create connection */
	public void configure(String url, String username, String password) {

		try {
			DriverManager.registerDriver(new FabricMySQLDriver());
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		}
	}

	public Connection getConnection() {
		return connection == null ? null : connection;
	}

	/** Close connection */
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	private DBConnector() {
	}

	/** Return unique instance of DBConnetor */
	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}

}
