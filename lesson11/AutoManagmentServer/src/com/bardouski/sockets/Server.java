package com.bardouski.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.facade.IFacade;
import com.bardouski.propertiesholder.PropertiesContext;

/**
 * At first initialize Facade and properties. Than init ServerSocket and start
 * to find connections. When found creates a new one client thread @see
 * ClientThread#ClientThread by constructor with client socket and methodds
 * dispather @see ClientThread#ClientThread(Socket, IFacade)
 */
public class Server {

	private static final String CONFIG = "resources\\config.properties";
	private static final String CONTEXT = "resources\\context.properties";

	private static Logger logger = Logger.getLogger(Server.class.getSimpleName());
	static final int PORT = 6666;

	public static void main(String[] args) throws IOException {

		new PropertiesContext(CONTEXT);
		ProgramProps.loadPropertiesByPath(CONFIG);
		
		try {
			DBConnector.configure(ProgramProps.valueOf("dbUrl"), ProgramProps.valueOf("dbUser"), ProgramProps.valueOf("dbPassword"));
		} catch (SQLException e1) {
			logger.error(e1.getMessage());
		}
		
		IFacade facade;
		try {
			facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
			if (facade == null) {
				throw new NoSuchObjectException();
			}

		} catch (ClassNotFoundException | NoSuchObjectException e1) {
			logger.fatal(e1.getMessage());
			System.err.println("Error initializing facade.");
			return;
		} 

		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started");

		try {
			while (true) {

				System.out.println("Server try to find any connection...");
				Socket socket = serverSocket.accept();

				System.out.println("Server found client socket: " + socket.toString() + "...");

				try {
					new ClientThread(socket, facade);
				} catch (IOException e) {
					socket.close();
				}
			}
		} finally {
			serverSocket.close();
			try {
				DBConnector.closeConnection();
			} catch (SQLException e) {
				
			}
		}
	}
}
