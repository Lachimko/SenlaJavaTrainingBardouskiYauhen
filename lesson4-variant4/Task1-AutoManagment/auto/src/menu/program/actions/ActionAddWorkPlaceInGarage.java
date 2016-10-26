package menu.program.actions;

import java.util.Scanner;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionAddWorkPlaceInGarage extends FacadeAction{

	private static final String MESSAGE = "Enter Garage ID where need to add workplace: ";
	
	public ActionAddWorkPlaceInGarage(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		System.out.println(MESSAGE);
		facade.addWorkPlaceInGarage(new Scanner(System.in).nextInt());
	}

}
