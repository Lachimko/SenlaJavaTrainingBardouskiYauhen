package bardouski.senla.training.model;

public class Mechanic {

	private static final String PATTERN_CLASSNAME = "Mechanic/";
	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_FULLNAME = "fullName=";
	private static final String PATTERN_ORDER = "currentOrder=";
	private static final String PATTERN_ENDFIELD = ";";

	private static int idCount = 1;
	private int id;
	private String fullName;
	private Order currentOrder;

	// converter constructor without id increment
	public Mechanic(int id, String fullName, Order currentOrder) {
		this.id = id;
		this.fullName = fullName;
		this.currentOrder = currentOrder;
	}

	// converter constructor without id increment
	public Mechanic(int id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public Mechanic(String fullName) {
		this.id = idCount;
		idCount++;
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public int getId() {
		return id;
	}

	public static void setIdCount(int thisClassObjectCount) {
		idCount += thisClassObjectCount;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_ID).append(this.id).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_FULLNAME).append(this.fullName).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_ORDER);

		if (this.currentOrder == null) {
			sb.append("null");
		} else {
			sb.append(this.currentOrder.getId());
		}
		sb.append(PATTERN_ENDFIELD);

		return sb.toString();

	}

}
