package com.bardouski.classes.propertiesholder;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class PropertiesContext {

	private PropertiestHolder properties;
	private Map<String, Object> classes = new HashMap<>();

	public PropertiesContext(String contextFilePath) throws FileNotFoundException {
		properties = new PropertiestHolder(contextFilePath);
	}

	public Object getInstance(String clazz) {

		String className = null;

		if (classes.containsKey(clazz)) {
			return classes.get(clazz);
		} else {

			try {
				className = properties.getProperty(clazz);
				if (className != null) {
					Object created = Class.forName(className).newInstance(); 
					classes.put(clazz, created);
					return created; 
				} else {
					return null;
				}

			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				return null;
			}

		}
	}

}
