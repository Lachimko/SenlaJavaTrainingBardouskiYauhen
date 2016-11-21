package com.bardouski.classes.propertiesholder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/** Contains properties loaded by path to file with properties */
public class PropertiestHolder {

	private String propertiesFilePath;
	private Properties properties = null;

	public PropertiestHolder(String propertiesFilePath) throws FileNotFoundException {

		if (new File(propertiesFilePath).exists()) {
			this.propertiesFilePath = propertiesFilePath;

		} else {
			throw new FileNotFoundException();
		}
	}

	/**
	 * Get property value of inputed key. If local property object contains key,
	 * return value of that property. If local property does not contain key,
	 * return null. If local properties does not exist, load them from property
	 * file, and set to local
	 */
	public String getProperty(String propertyKey) {

		if (properties == null) {

			properties = new Properties();
			try (FileInputStream stream = new FileInputStream(propertiesFilePath)) {

				properties.load(stream);

			} catch (IOException e) {
				properties = null;
			}

		}
		
		return properties.getProperty(propertyKey);
	}

}
