package com.bardouski.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
import com.bardouski.facade.IFacade;
import com.bardouski.hibernate.initializer.HibernateUtil;
import com.bardouski.propertiesholder.PropertiesContext;

/**
 * At first initialize Facade and properties. Than init ServerSocket and start
 * to find connections. When found creates a new one client thread @see
 * ClientThread#ClientThread by constructor with client socket and methodds
 * dispather @see ClientThread#ClientThread(Socket, IFacade)
 */
public class Server {

	private static final String CONFIG = "src//main//resources//config.properties";
	private static final String CONTEXT = "src//main//resources//context.properties";
	private static final int PORT = 6666;

	private static Logger logger = Logger.getLogger(Server.class.getSimpleName());

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		PropertiesContext context = new PropertiesContext(CONTEXT);
		ProgramProps.loadPropertiesByPath(CONFIG);

		IFacade facade;
		HibernateUtil hibernate;

		try {
			hibernate = (HibernateUtil) PropertiesContext.getInstance(HibernateUtil.class);
			// hibernate = HibernateUtil.getInstance();
			facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
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
