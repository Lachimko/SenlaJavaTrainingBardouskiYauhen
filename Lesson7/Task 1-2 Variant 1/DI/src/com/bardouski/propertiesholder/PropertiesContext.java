package com.bardouski.propertiesholder;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * PropertiesContext contains itself container of Properties with Interfaces
 * implementations. Using constructor with @param contextFilePath @see
 * PropertiesContext#PropertiesContext(String) means creating new
 * PropertiestHolder object with passed in constructor @param contextFilePath in
 * PropertiesContext constructor. Properties cannot be instantiating if
 * PropertiesContext constructor @see
 * PropertiesContext#PropertiesContext(String) throws FileNotFoundException.
 */
public class PropertiesContext {

	private static PropertiestHolder properties;
	private static Map<String, Object> classes = new HashMap<>();

	public PropertiesContext(String contextFilePath) throws FileNotFoundException {
		properties = new PropertiestHolder(contextFilePath);
	}

	/**
	 * Method return an instance, created by default empty constructor of
	 * appropriate Class. During creating instances, that instances goes to
	 * local Map @see PropertiesContext#classes, on condition if they weren't in
	 * that Map.
	 * 
	 * Returns null if there are not such class implementation in
	 * properties @see PropertiesContext#properties, and some Exception was
	 * caught.
	 * 
	 * @throws InstantiationException
	 *             - if specified class object cannot be instantiated
	 * @throws IllegalAccessException
	 *             - if there are no access to default class constructor
	 * @throws ClassNotFoundException
	 *             - if such class was not found
	 */
	public static Object getInstance(Class<?> clazz) throws ClassNotFoundException {

		String simpleClassName = String.valueOf(clazz.getSimpleName());

		if (classes.containsKey(simpleClassName)) {
			return classes.get(simpleClassName);
		} else {

			try {
				String implementation = properties.getProperty(simpleClassName);

				if (implementation == null) {
					return null;

				} else {
					Object created = Class.forName(implementation).newInstance();
					classes.put(simpleClassName, created);
					return created;
				}

			} catch (InstantiationException | IllegalAccessException e) {
				return null;
			}

		}
	}

}
