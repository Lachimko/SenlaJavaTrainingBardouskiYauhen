package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import menu.program.actions.parents.FacadeInputAction;

public class DelayOrdersAction extends FacadeInputAction{

	private static final String HOW_MANY_DAYS = "For how many days delay other orders:";
	private static final String DELAYED_ID_REQUEST = "Input Id of delayed order";

	public DelayOrdersAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(DELAYED_ID_REQUEST);
		try {
			Order delayed = facade.getOrder(scanner.nextInt());
			printer.print(HOW_MANY_DAYS);
			facade.replaceDatesOfOrdersFrom(delayed, scanner.nextInt());
		} catch (NoSuchObjectException e) {
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}
}
