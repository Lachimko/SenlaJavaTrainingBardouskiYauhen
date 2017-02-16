package com.bardouski.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
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

	private static Logger logger = Logger.getLogger("MultiJabberServer");
	static final int PORT = 6666;

	public static void main(String[] args) throws IOException {

		new PropertiesContext("resources\\context.properties");
		ProgramProps.loadPropertiesByPath("resources\\config.properties");

		IFacade facade;

		try {

			facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
			if (facade == null) {
				throw new NoSuchObjectException();
			}

		} catch (ClassNotFoundException e1) {
			logger.fatal(e1.getMessage());
			System.err.println("Error initializing facade.");
			return;
		} catch (NoSuchObjectException e1) {
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
		}
	}
}
