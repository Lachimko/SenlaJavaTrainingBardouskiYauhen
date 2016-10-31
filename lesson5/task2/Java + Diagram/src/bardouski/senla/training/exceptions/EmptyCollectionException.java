package bardouski.senla.training.exceptions;

public class EmptyCollectionException extends Exception{

	private static final String MESSAGE = "ERROR: You trying to process empty collection\n";

	public String getMessage(){
		return MESSAGE;
	}
}
