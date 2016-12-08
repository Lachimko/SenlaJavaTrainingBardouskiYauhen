package com.bardouski.dbprocessor.serializator;

import java.util.List;

import com.bardouski.model.IGarage;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;

/**Container for result lists from serialization 
 */
public interface IResultContainer {

	List<? extends IMechanic> getResultMechanics();

	void setResultMechanics(List<? extends IMechanic> resultMechanics);

	List<? extends IOrder> getResultOrders();

	void setResultOrders(List<? extends IOrder> resultOrders);

	List<? extends IGarage> getResultGarages();

	void setResultGarages(List<? extends IGarage> resultGarages);

}
