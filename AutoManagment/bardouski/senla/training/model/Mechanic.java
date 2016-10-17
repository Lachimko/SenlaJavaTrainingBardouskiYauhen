package bardouski.senla.training.model;

public class Mechanic {

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

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Mechanic{");
		sb.append("Id: ").append(this.getId()).append(", Fullname: ").append(this.getFullName())
				.append(", Current work: ").append(this.getCurrentOrder()).append("}\n");
		return sb.toString();

	}

}
