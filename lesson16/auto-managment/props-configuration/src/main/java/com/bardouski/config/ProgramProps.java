package com.bardouski.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Contains itself type values of properties. Note for initializing: Search in
 * root classpath file with name "config.properties"
 */
public final class ProgramProps {

	private Properties properties;
	private String propsPath = "config.properties";

	@SuppressWarnings("unused")
	private ProgramProps() {
	}

	public ProgramProps(String propertiesPath) {
		configure(propsPath);
	}

	public boolean isPropsLoaded() {
		return (properties == null) ? false : true;
	}

	public String getPropertiesPath() {
		return propsPath;
	}

	public String valueOf(String propertyKey) {
		return (isPropsLoaded()) ? properties.getProperty(propertyKey) : null;
	}

	public void configure(String propertiesPath) {

		if (propertiesPath != null) {
			propsPath = propertiesPath;
		}
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propertiesPath);
		try {
			properties = new Properties();
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
