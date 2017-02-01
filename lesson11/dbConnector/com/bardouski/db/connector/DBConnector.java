package com.bardouski.db.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

/** Class connector to mysql database */
public final class DBConnector {

	private static Connection connection;

	public static void configure(String url, String username, String password) throws SQLException {

		DriverManager.registerDriver(new FabricMySQLDriver());
		connection = DriverManager.getConnection(url, username, password);
	}

	public static Connection getConnection() {
		return connection == null ? null : connection;
	}
	
	public static void closeConnection() throws SQLException{
		if (connection != null){
			connection.close();
		}
	}
	
	private DBConnector(){
	}
	
}
