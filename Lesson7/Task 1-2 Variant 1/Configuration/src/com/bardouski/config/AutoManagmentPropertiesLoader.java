package com.bardouski.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Load properties file by constructor. Connect to prop file, convert types of
 * returned properties values. Method getProperties() return
 * AutoManagmentPropertiesHolder
 */
public class AutoManagmentPropertiesLoader {

	private static AutoManagmentPropertiesHolder properties = null;

	public static void loadPropertiesPath(String propertiesPath) {

		Properties property = new Properties();
		try (FileInputStream fis = new FileInputStream(propertiesPath)) {

			property.load(fis);

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		boolean addRemoveWorkpalces = Boolean.valueOf(property.getProperty("addRemoveWorkpalces"));
		boolean delayOrders = Boolean.valueOf(property.getProperty("delayOrders"));
		boolean removeOrder = Boolean.valueOf(property.getProperty("removeOrder"));

		properties = new AutoManagmentPropertiesHolder(property.getProperty("dbPath"), property.getProperty("dbCSVPath"), addRemoveWorkpalces, removeOrder,
				delayOrders);
	}

	public static AutoManagmentPropertiesHolder getProperties() {
		return AutoManagmentPropertiesLoader.properties;
	}
}
