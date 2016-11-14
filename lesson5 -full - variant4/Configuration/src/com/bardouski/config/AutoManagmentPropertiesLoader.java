package com.bardouski.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Load properties file by constructor. Connect to prop file, convert types of
 * returned properties values. Method getProperties() return
 * AutoManagmentPropertiesHolder
 */
public class AutoManagmentPropertiesLoader {

	private static final String CLASS_NAME = "PropertiesLoader";
	private static final String EQUALS_TRUE = "true";
	
	private static AutoManagmentPropertiesHolder properties = null;

	public static void loadPropertiesPath(String propertiesPath) {

		Properties property = new Properties();
		try (FileInputStream fis = new FileInputStream(propertiesPath)) {

			property.load(fis);

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			Logger.getLogger(CLASS_NAME).error(e.getMessage());
		}

		boolean addRemoveWorkpalces = false;
		boolean delayOrders = false;
		boolean removeOrder = false;

		if (property.getProperty("addRemoveWorkpalces").equals(EQUALS_TRUE)) {
			addRemoveWorkpalces = true;
		}
		if (property.getProperty("delayOrders").equals(EQUALS_TRUE)) {
			delayOrders = true;
		}
		if (property.getProperty("removeOrder").equals(EQUALS_TRUE)) {
			removeOrder = true;
		}

		properties = new AutoManagmentPropertiesHolder(property.getProperty("dbPath"), addRemoveWorkpalces, removeOrder,
				delayOrders);

	}

	public static AutoManagmentPropertiesHolder getProperties() {
		return AutoManagmentPropertiesLoader.properties;
	}
}
