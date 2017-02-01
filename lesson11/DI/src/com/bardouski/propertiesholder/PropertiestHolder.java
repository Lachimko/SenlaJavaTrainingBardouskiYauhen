package com.bardouski.propertiesholder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Contains properties loaded by transfered into constructor @see
 * PropertiestHolder#PropertiestHolder(String) string representation of path to
 * file with properties @param propertiesFilePath. If this file doesn't exist in
 * filesystem - throws FileNotFoundException, if exist - load data to
 * local @param properties
 */
public class PropertiestHolder {

	private Properties properties;

	public PropertiestHolder(String propertiesFilePath) throws FileNotFoundException {

		if (new File(propertiesFilePath).exists()) {
			properties = new Properties();
			try (FileInputStream stream = new FileInputStream(propertiesFilePath)) {

				properties.load(stream);

			} catch (IOException e) {
				properties = null;
			}

		} else {
			throw new FileNotFoundException();
		}
	}

	/**
	 * Get property value of inputed key. If local property object contains
	 * key @param propertyKey, return value of that property. If local property
	 * does not contain key, return null.
	 */
	public String getProperty(String propertyKey) {
		return (properties == null) ? null : properties.getProperty(propertyKey);
	}

}
