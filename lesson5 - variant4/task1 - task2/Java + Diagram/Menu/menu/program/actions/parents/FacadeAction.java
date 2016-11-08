package menu.program.actions.parents;

import org.apache.log4j.Logger;

import bardouski.senla.training.facade.Facade;
import menu.program.printer.IPrinter;
import menu.program.printer.SysoutPrinter;

public abstract class FacadeAction implements IAction {

	protected static Logger logger = null;
	
	protected static IPrinter printer = null;
	protected Facade facade;

	public FacadeAction(Facade facade) {
		this.facade = facade;
		
		if (printer == null) {
			printer = new SysoutPrinter();
		}
		
		if (logger == null) {
			logger = Logger.getLogger(FacadeAction.class.getSimpleName());
		}
	}
	
	public void printFail(String message){
		printer.print("FAIL: " + message);
	};

}
