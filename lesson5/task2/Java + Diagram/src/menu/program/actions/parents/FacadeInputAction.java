package menu.program.actions.parents;

import java.util.Scanner;

import bardouski.senla.training.facade.Facade;

public abstract class FacadeInputAction extends FacadeAction{

	protected static Scanner scanner = null;
	
	public FacadeInputAction(Facade facade) {
		super(facade);
		
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
	}

}
