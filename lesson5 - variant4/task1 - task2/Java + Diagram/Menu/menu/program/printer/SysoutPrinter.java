package menu.program.printer;

import java.util.List;

public class SysoutPrinter implements IPrinter {

	@Override
	public void print(Object object) {
		System.out.println(object.toString());
	}

	@Override
	public void print(List<Object> object) {

		for (Object item : object) {
			System.out.println(item.toString());
		}
	}

	@Override
	public void printFail(String message) {
		System.err.println(message);
		
	}

}
