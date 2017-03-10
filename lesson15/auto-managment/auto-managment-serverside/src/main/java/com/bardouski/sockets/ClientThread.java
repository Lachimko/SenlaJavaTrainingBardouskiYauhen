package com.bardouski.sockets;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;

//import com.bardouski.facade.IFacade;
//import com.bardouski.requests.Request;
//import com.bardouski.responses.Response;

public class ClientThread extends Thread {

//	private Socket socket;
//	private ObjectInputStream in;
//	private ObjectOutputStream out;
//	private ResponseHandler handler;
//
//	public ClientThread(Socket s, IFacade facade) throws IOException {
//
//		socket = s;
//		out = new ObjectOutputStream(socket.getOutputStream());
//		in = new ObjectInputStream(socket.getInputStream());
//		handler = new ResponseHandler(facade);
//
//		start();
//	}
//
//	public void run() {
//
//		Request request;
//
//		try {
//			while ((request = (Request) in.readObject()) != null) {
//				Response response = handler.executeAndReturnResponce(request);
//
//				if (response.getResponceResult() != null){
//					out.writeObject(response);
//				}
//			}
//
//		} catch (ClassNotFoundException | IOException e) {
//			System.err.println(e);
//		}
//
//	}
}
