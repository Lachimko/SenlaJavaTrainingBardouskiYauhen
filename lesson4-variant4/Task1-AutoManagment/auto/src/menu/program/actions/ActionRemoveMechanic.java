package menu.program.actions;

import java.util.Scanner;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionRemoveMechanic extends FacadeAction {

	private static final String MESSAGE = "Enter Mechnanic's ID to remove: ";
	
	public ActionRemoveMechanic(Facade facade) {
		super(facade);
	}
	
	@Override
	public void execute() {

		System.out.print(MESSAGE);
		facade.removeMechanic(new Scanner(System.in).nextInt());
	}

}
