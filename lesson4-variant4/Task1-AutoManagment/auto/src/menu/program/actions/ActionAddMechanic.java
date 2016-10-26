package menu.program.actions;

import java.util.Scanner;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionAddMechanic extends FacadeAction{

	private static final String MESSAGE = "Enter Mechnanic FullName: ";
	
	public ActionAddMechanic(Facade facade) {
		super(facade);
	}
	
	@Override
	public void execute() {
		
		System.out.print(MESSAGE);
		facade.addMechanic(new Scanner(System.in).nextLine());
	}

}
