package bardouski.senla.training.converter;

public class Converter {

	private MechanicConverter mechanicConverter;
	private OrderConverter orderConverter;
	private TaskConverter taskConverter;

	public Converter() {

		mechanicConverter = new MechanicConverter();
		orderConverter = new OrderConverter();
		taskConverter = new TaskConverter();
	}

	public MechanicConverter getMechanicConverter() {
		return mechanicConverter;
	}

	public OrderConverter getOrderConverter() {
		return orderConverter;
	}

	public TaskConverter getTaskConverter() {
		return taskConverter;
	}

}
