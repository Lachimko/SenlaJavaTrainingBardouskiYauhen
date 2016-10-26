package menu.program.actions;

import java.util.Scanner;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionRemoveWorkPlaceInGarage extends FacadeAction{

	private static final String MESSAGE_WORKPLACE = "ID of Workplace to remove: ";
	private static final String MESSAGE_GARAGE = "ID of Garage in which remove: ";
	
	public ActionRemoveWorkPlaceInGarage(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		System.out.println(MESSAGE_WORKPLACE);
		int wpID = new Scanner(System.in).nextInt();
		System.out.println(MESSAGE_GARAGE);
		int garID = new Scanner(System.in).nextInt();
		
		facade.removeWorkPlaceInGarage(wpID, garID);
	}

}
