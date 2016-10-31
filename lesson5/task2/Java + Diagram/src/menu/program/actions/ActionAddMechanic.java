package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import menu.program.actions.parents.FacadeInputAction;

public class ActionAddMechanic extends FacadeInputAction{

	private static final String MESSAGE = "Enter Mechnanic FullName: ";
	
	public ActionAddMechanic(Facade facade) {
		super(facade);
	}
	
	@Override
	public void execute() {
		
		printer.print(MESSAGE);
		Mechanic mechanic = new Mechanic(scanner.nextLine());
		facade.addMechanic(mechanic);
	}

}
