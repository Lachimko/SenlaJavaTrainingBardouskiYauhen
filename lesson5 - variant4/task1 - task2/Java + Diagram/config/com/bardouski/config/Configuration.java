package com.bardouski.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	public static Properties getProperties() throws IOException{

			Properties property = new Properties();
			FileInputStream fis = new FileInputStream("src\\config.properties");
			property.load(fis);
			
			return property;
	}
}
