package bardouski.senla.training.converter;

import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;

public class OrderConverter extends AbstractConverter<Order> {

	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_ENDFIELD = ";";

	@Override
	public String serialize(Order goal) {
		return goal.toString();
	}

	@Override
	public Order deserialize(String goal) {

		int orderId;

		int startFieldPosition;
		int endFieldPosition;
		OrderStatus orderStatus = OrderStatus.INPROGRESS;

		// order ID parse
		startFieldPosition = goal.indexOf(PATTERN_ID) + PATTERN_ID.length();
		endFieldPosition = goal.indexOf(PATTERN_ENDFIELD, startFieldPosition);
		orderId = Integer.parseInt((goal.substring(startFieldPosition, endFieldPosition)));

		// For deconversion not used other fields of Order fields such TASK,
		// MECHANIC, WORKPLACE

		return new Order(orderId, null, null, null, orderStatus);
	}

}
