package menu.program.actions;

import java.util.Scanner;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionReturnOrderOfMechanic extends FacadeAction{

	private static final String MESSAGE = "Mechanic's Id: ";
	
	public ActionReturnOrderOfMechanic(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		System.out.println(MESSAGE);
		facade.returnOrderOfMechanic(new Scanner(System.in).nextInt());
		
	}

}
