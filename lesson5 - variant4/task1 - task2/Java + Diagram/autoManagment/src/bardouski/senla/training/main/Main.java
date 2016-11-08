package bardouski.senla.training.main;

import java.util.Properties;

import com.bardouski.config.Configuration;
import bardouski.senla.training.facade.Facade;
import menu.program.menuprocessor.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Properties configuration = Configuration.getProperties();
		
		Facade facade = new Facade(configuration.getProperty("dbPath"));
		MenuBuilder builder = new MenuBuilder(facade, configuration);
		MenuProcessor menuProcessor = new MenuProcessor(builder.buildMenu());
		menuProcessor.process();
		facade.saveToFile();
		
	}

}
