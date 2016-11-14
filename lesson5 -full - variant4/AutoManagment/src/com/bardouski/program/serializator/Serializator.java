package com.bardouski.program.serializator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;


public class Serializator implements ISerializer {
	
	private Logger logger = Logger.getLogger(Serializator.class.getSimpleName());
	
	private static final String IO_ERROR = "IO Error";
	private static final String NO_DB_FILE = "DB File did not find";

	private String dbPath;
	
	public Serializator(String dbPath) throws FileNotFoundException {
		this.dbPath = dbPath;
		
		if (!(new File(dbPath)).exists()) {
			logger.fatal(NO_DB_FILE);
			throw new FileNotFoundException();
		} 
	}

	/**Deserialise data from file. Return collections in box FacadeResltContainer*/
	@SuppressWarnings("unchecked")
	public FacadeResultContainer getLists() {
		
		try(FileInputStream fileInputStream = new FileInputStream(dbPath);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);){
			
			List<Mechanic> mechanics = (List<Mechanic>) objectInputStream.readObject();
			List<Order> orders = (List<Order>) objectInputStream.readObject();
			List<Garage> garages = (List<Garage>) objectInputStream.readObject();
			
			if (mechanics.isEmpty()){
				mechanics = new ArrayList<>();
			}
			if (orders.isEmpty()){
				orders = new ArrayList<>();
			}
			if (garages.isEmpty()){
				garages = new ArrayList<>();
			}
			
			return new FacadeResultContainer(mechanics, orders, garages);
			
		} catch (IOException e) {
			logger.error("IO Exception throwed in getLists");
		} catch (ClassNotFoundException e) {
			logger.fatal("Serialization fail");
		}
		return null; 
	}

	/**Serialize and write to DB lists from params*/
	public void saveToFile(IResultContainer container){

		FacadeResultContainer containerToWrite = (FacadeResultContainer)container;
		
		try(FileOutputStream fileOutputStream = new FileOutputStream(dbPath);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){
			
			objectOutputStream.writeObject(containerToWrite.getResultMechanics());
			objectOutputStream.writeObject(containerToWrite.getResultOrders());
			objectOutputStream.writeObject(containerToWrite.getResultGarages());
		
		} catch (FileNotFoundException e) {
			logger.fatal(NO_DB_FILE);
		} catch (IOException e) {
			logger.error(IO_ERROR);
		}
	}

}
