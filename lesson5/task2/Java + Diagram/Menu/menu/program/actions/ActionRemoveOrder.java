package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import menu.program.actions.parents.FacadeInputAction;

public class ActionRemoveOrder extends FacadeInputAction{

	public ActionRemoveOrder(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		try {
			Order order = facade.getOrder(scanner.nextInt());
			facade.removeOrder(order);
		} catch (NoSuchObjectException e) {
			e.getMessage();
		}
	}
}
