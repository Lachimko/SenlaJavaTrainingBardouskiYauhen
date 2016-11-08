package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import menu.program.actions.parents.FacadeInputAction;

public class AddMechanicAction extends FacadeInputAction{

	private static final String MESSAGE = "Enter Mechnanic FullName: ";
	
	public AddMechanicAction(Facade facade) {
		super(facade);
	}
	
	@Override
	public void execute() {
		
		printer.print(MESSAGE);
		facade.addMechanic(new Mechanic(scanner.nextLine()));
	}

}
