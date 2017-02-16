package com.bardouski.ui.main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.menuprocessor.IMenuBuilder;
import com.bardouski.ui.menuprocessor.IMenuProcessor;
import com.bardouski.ui.menuprocessor.MenuBuilder;
import com.bardouski.ui.menuprocessor.MenuProcessor;

public class Main {

	private static final String CONNECTION_ERROR = "Server not started, or server is not waiting to accept connections, or wrong port number.";
	private static final String WRONG_IP = "IP address of a host could not be determined";

	private static final int SERVER_PORT = 6666;
	private static final String IP_ADRESS = null;
	private static Logger logger = Logger.getLogger("ClientSideMainClass");

	public static void main(String[] args) throws Exception {

		Socket socket;
		InetAddress ipAddress;

		try {
			ipAddress = InetAddress.getByName(IP_ADRESS);
		} catch (UnknownHostException e) {
			System.err.println(WRONG_IP);
					logger.error(WRONG_IP);
			return;
		}

		try {
			socket = new Socket(ipAddress, SERVER_PORT);
					logger.info("Socket created: " + socket.toString());

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
					logger.info("Socket IS: " + in.toString() + "\nSocket OS:" + out.toString());

			ProgramProps.loadPropertiesByPath("resources\\config.properties");
			new PropertiesContext("resources\\context.properties");
					logger.info("Configuration of context succesfull completed.");
			
			IMenuBuilder builder = (MenuBuilder) PropertiesContext.getInstance(IMenuBuilder.class);
			builder.setStreams(in, out);

			MenuProcessor menuProcessor = (MenuProcessor) PropertiesContext.getInstance(IMenuProcessor.class);
			menuProcessor.setRoot(builder.buildMenu());
					logger.info("Menu built: " + menuProcessor.toString());
			
			menuProcessor.process();


		} catch (ConnectException e) {
			System.err.println(CONNECTION_ERROR);
			logger.error(CONNECTION_ERROR);
		} catch (Exception x) {
			x.printStackTrace();
		}

	}

}
