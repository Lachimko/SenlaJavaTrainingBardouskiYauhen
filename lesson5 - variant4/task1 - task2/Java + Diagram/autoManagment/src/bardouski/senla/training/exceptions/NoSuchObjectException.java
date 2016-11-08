package bardouski.senla.training.exceptions;

@SuppressWarnings("serial")
public class NoSuchObjectException extends Exception{

	private static final String MESSAGE = "INFO: No such element.\n";

	public String getMessage(){
		return MESSAGE;
	}
}
