package com.bardouski.program.dbprocessor.serializator;

import java.io.EOFException;
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

import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.dbprocessor.serializator.ISerializer;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Order;

public class Serializator implements ISerializer {

	private Logger logger;

	private static final String IO_ERROR = "IO Error";
	private static final String NO_DB_FILE = "DB File did not find";

	private String dbPath;

	public Serializator() {
	}

	@Override
	public void setPath(String path) throws FileNotFoundException {

		if (!(new File(path)).exists()) {
			logger = Logger.getLogger(Serializator.class.getSimpleName());
			logger.error(NO_DB_FILE);
			throw new FileNotFoundException();
		}

		this.dbPath = path;
	}

	/**
	 * Deserialise data from file. Return collections in box
	 * FacadeResltContainer
	 */
	@SuppressWarnings("unchecked")
	@Override
	public FacadeResultContainer getLists() {

		try (FileInputStream fileInputStream = new FileInputStream(dbPath);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			List<Mechanic> mechanics = (List<Mechanic>) objectInputStream.readObject();
			List<Order> orders = (List<Order>) objectInputStream.readObject();
			List<Garage> garages = (List<Garage>) objectInputStream.readObject();

			return new FacadeResultContainer(mechanics, orders, garages);

		} catch (EOFException e) {
			return new FacadeResultContainer(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		} catch (IOException e) {
			logger.error("IO Exception throwed in getLists");
		} catch (ClassNotFoundException e) {
			logger.fatal("Serialization fail");
		}
		return new FacadeResultContainer(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	/** Serialize and write to DB lists from params */
	@Override
	public void saveToFile(IResultContainer container) {

		FacadeResultContainer containerToWrite = (FacadeResultContainer) container;

		try (FileOutputStream fileOutputStream = new FileOutputStream(dbPath);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

			objectOutputStream.writeObject(containerToWrite.getResultMechanics());
			objectOutputStream.writeObject(containerToWrite.getResultOrders());
			objectOutputStream.writeObject(containerToWrite.getResultGarages());

		} catch (FileNotFoundException e) {
			logger = Logger.getLogger(Serializator.class.getSimpleName());
			logger.fatal(NO_DB_FILE);
		} catch (IOException e) {
			logger = Logger.getLogger(Serializator.class.getSimpleName());
			logger.error(IO_ERROR);
		}
	}

}
