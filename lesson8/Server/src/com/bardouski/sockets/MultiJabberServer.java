package com.bardouski.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.facade.IFacade;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.requests.RequestContainer;
import com.bardouski.responses.ResponseContainer;

class ClientThread extends Thread {

	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private IFacade facade;

	public ClientThread(Socket s, IFacade facade) throws IOException {

		this.facade = facade;
		socket = s;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

		start();
	}

	public void run() {

		RequestContainer answer;

		try {

			while ((answer = (RequestContainer) in.readObject()) != null) {
				
				ResponseContainer responseContainer = new ResponseContainer(answer, facade);
				responseContainer.execute();
				System.out.println(answer);
			}

		} catch (ClassNotFoundException | IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}
}

public class MultiJabberServer {

	private static Logger logger = Logger.getLogger("MultiJabberServer");
	static final int PORT = 6666;

	public static void main(String[] args) throws IOException {

		PropertiesContext context = new PropertiesContext("resources\\context.properties");
		ProgramProps.loadPropertiesByPath("resources\\config.properties");

		IFacade facade;

		try {
			facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
			logger.info("Facade Initialized.");
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

		logger.info("Server Socket started: " + serverSocket.toString());
		System.out.println("Server Started");

		try {
			while (true) {

				System.out.println("Server try to find any connection...");
				Socket socket = serverSocket.accept();
				logger.info("Server find client: " + socket.toString());

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
