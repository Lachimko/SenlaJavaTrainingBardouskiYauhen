package menu.program.actions.parents;

import bardouski.senla.training.facade.Facade;
import menu.program.scanner.MultiScanner;

public abstract class FacadeInputAction extends FacadeAction{

	protected MultiScanner scanner = null;
	
	public FacadeInputAction(Facade facade) {
		super(facade);
		
		if (scanner == null) {
			scanner = new MultiScanner();
		}
	}

}
