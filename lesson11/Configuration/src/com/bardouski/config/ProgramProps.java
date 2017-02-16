package com.bardouski.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**Contains itself type values of properties*/
public final class ProgramProps {

	private static Properties properties;

	private ProgramProps(){
	}
	
	public static ProgramProps loadPropertiesByPath(String pathToPropertiesFile) throws FileNotFoundException{
		
		if (new File(pathToPropertiesFile).exists()) {
			properties = new Properties();
			try (FileInputStream stream = new FileInputStream(pathToPropertiesFile)) {

				properties.load(stream);

			} catch (IOException e) {
				properties = null;
			}

		} else {
			throw new FileNotFoundException();
		}
		return null;
	}
	
	public static String valueOf(String propertyKey){
		return (properties == null) ? null : properties.getProperty(propertyKey);
	}

}
