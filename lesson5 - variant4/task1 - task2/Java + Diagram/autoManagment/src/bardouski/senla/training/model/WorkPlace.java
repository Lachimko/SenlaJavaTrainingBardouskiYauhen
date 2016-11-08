package bardouski.senla.training.model;

import java.io.Serializable;

public class WorkPlace implements Serializable{

	private static final String PATTERN_CLASSNAME = "WorkPlace/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_ORDER = "order=";
	private static final String PATTERN_NULL = "null";
	private static final String PATTERN_ENDFIELD = ";";

	private static int idCount = 1;

	private int id;
	private Order order;

	// converter constructor without id increment
	public WorkPlace(int id) {
		this.id = id;
		idCount++;
	}

	// converter constructor without id increment
	public WorkPlace(int id, Order order) {
		this.id = id;
		idCount++;
		this.order = order;
	}

	public WorkPlace() {
		this.id = idCount;
		idCount++;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public static void setIdCount(int idCount) {
		WorkPlace.idCount = idCount;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_ID).append(this.id).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_ORDER);

		if (this.order == null) {
			sb.append(PATTERN_NULL);
		} else {

			sb.append(this.order.getId());
		}
		sb.append(PATTERN_ENDFIELD);

		return sb.toString();

	}

}
