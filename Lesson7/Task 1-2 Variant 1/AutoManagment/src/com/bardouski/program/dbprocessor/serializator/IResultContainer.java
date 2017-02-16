package com.bardouski.program.dbprocessor.serializator;

import java.util.List;

import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

/**Container for result lists from serialization 
 */
public interface IResultContainer {

	List<Mechanic> getResultMechanics();

	void setResultMechanics(List<Mechanic> resultMechanics);

	List<Order> getResultOrders();

	void setResultOrders(List<Order> resultOrders);

	List<Garage> getResultGarages();

	void setResultGarages(List<Garage> resultGarages);

}
