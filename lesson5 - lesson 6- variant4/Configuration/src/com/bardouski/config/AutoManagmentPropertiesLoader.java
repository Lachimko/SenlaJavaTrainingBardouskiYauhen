package com.bardouski.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Load properties file by constructor. Method getProperties() - connect to prop
 * file, convert types of returned properties values and return
 * AutoManagmentPropertiesHolder
 */
public class AutoManagmentPropertiesLoader implements IPropertyLoader {

	private static final String TRUE_PARSE_MASK = "true";

	private static final String NO_PROPERTIES_FILE = "No properties file found";

	private String propertiesPath;
	private Logger logger = Logger.getLogger("AutoManagmentPropertyLoader");

	public AutoManagmentPropertiesLoader(String propertiesPath) {
		this.propertiesPath = propertiesPath;
	}

	/**
	 * connect to prop file, convert types of returned properties values and
	 * return AutoManagmentPropertiesHolder
	 */
	public AutoManagmentPropertiesHolder getProperties() {

		Properties property = new Properties();
		try (FileInputStream fis = new FileInputStream(propertiesPath)) {
			property.load(fis);
		} catch (FileNotFoundException e) {
			logger.error(NO_PROPERTIES_FILE);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		boolean addRemoveWorkpalces = false;
		boolean delayOrders = false;
		boolean removeOrder = false;

		if (property.getProperty("addRemoveWorkpalces").contains(TRUE_PARSE_MASK)) {
			addRemoveWorkpalces = true;
		}
		if (property.getProperty("delayOrders").contains(TRUE_PARSE_MASK)) {
			delayOrders = true;
		}
		if (property.getProperty("removeOrder").contains(TRUE_PARSE_MASK)) {
			removeOrder = true;
		}

		return new AutoManagmentPropertiesHolder(property.getProperty("dbPath"), addRemoveWorkpalces, removeOrder,
				delayOrders);
	}
}
