package com.bardouski.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Expression;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bardouski.config.ProgramProps;
import com.bardouski.facade.IFacade;
import com.bardouski.hibernate.initializer.HibernateUtil;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Task;
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
			hibernate = HibernateUtil.getInstance();
			facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.fatal(e.getMessage());
			return;
		}
		
		
//		Mechanic mechanic = new Mechanic();
//		mechanic.setFullName("test mechanic");
		Task task = new Task();
		task.setRequestDate(new Date());
		task.setCompleteDate(new Date());
		task.setStartDate(new Date());
		task.setPrice(15);
		task.setToDo("test");
		task.setId(new Integer(1));

		Session session = hibernate.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(task);
		
		session.getTransaction().commit();
		session.close();
		
		
		Session session2 = hibernate.getSessionFactory().openSession();
		task.setPrice(1000);
		session2.save(task);
		session2.flush();
		session2.close();
		
		
//		ServerSocket serverSocket = new ServerSocket(PORT);
//		System.out.println("Server Started");
//
//		try {
//			while (true) {
//
//				System.out.println("Server try to find any connection...");
//				Socket socket = serverSocket.accept();
//
//				System.out.println("Server found client socket: " + socket.toString() + "...");
//
//				try {
//					new ClientThread(socket, facade);
//				} catch (IOException e) {
//					socket.close();
//				}
//			}
//		} finally {
//			serverSocket.close();
//		}
	}
}
