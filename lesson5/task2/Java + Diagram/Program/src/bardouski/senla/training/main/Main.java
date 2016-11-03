package bardouski.senla.training.main;

import bardouski.senla.training.facade.Facade;
import menu.program.menuprocessor.MenuBuilder;
import menu.program.menuprocessor.MenuProcessor;

public class Main {

	public static void main(String[] args) throws Exception {
		{
			if (args.length < 1) {
				args = new String[] { "" };
			}
		}

		Facade facade = new Facade(args[0]);
		
		MenuBuilder builder = new MenuBuilder(facade);
		MenuProcessor menuProcessor = new MenuProcessor(builder.buildMenu());
		menuProcessor.process();
		// facade.addGarage();
		// facade.addMechanic("fullName");
		// facade.addWorkPlaceInGarage(1);
		//
		// facade.addMechanic("FirstAdded");
		// facade.addMechanic("SecondAdded");
		//
		// facade.addGarage();
		// facade.addGarage();
		// facade.addWorkPlaceInGarage(1);
		// facade.addWorkPlaceInGarage(2);
		// facade.addOrder("Test task1", "20.10.2016", "26.10.2016", 48.5);
		// facade.addOrder("Test task2", "25.10.2016", "27.10.2016", 15.25);
		//
	}

}
