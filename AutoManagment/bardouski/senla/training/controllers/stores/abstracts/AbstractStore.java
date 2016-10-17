package bardouski.senla.training.controllers.stores.abstracts;

import bardouski.senla.training.controllers.managers.OrderManager;
import bardouski.senla.training.controllers.stores.interfaces.IStrore;
import bardouski.senla.training.model.db.DBDriver;

public abstract class AbstractStore<T> implements IStrore<T> {

	protected DBDriver dbDriver = OrderManager.getDbDriver();
	
	protected abstract String serialize(T object);

	protected abstract String returnStringFromLocalDB(T object);
}
