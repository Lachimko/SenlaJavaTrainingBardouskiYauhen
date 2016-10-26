package menu.program.menuprocessor;

import java.util.List;
import java.util.Scanner;

import menu.program.menuelements.MenuElement;

public class MenuProcessor {

	private static final String EXECUTED = "EXECUTED...";
	private static final String WRONG_VALUE = "WRONG VALUE";
	private MenuElement root;
	private MenuElement current;
	private Scanner scanner = new Scanner(System.in);

	public MenuProcessor(MenuElement root) {
		this.root = root;
		this.current = root;
	}

	public void start() {

		System.out.print("Choose need point from: ->");
		System.out.println(current.getCaption());
		System.out.println(printChilds(current));

		process();
	}

	private String printChilds(MenuElement elem) {

		StringBuilder sb = new StringBuilder();
		int i = 0;

		for (MenuElement menuElement : elem.getItems()) {

			sb.append(i++).append(". ").append(menuElement.getCaption()).append("\n");
		}

		return sb.toString();
	}

	public void process() {

		List<MenuElement> currents = current.getItems();

		if (scanner.hasNextInt()) {
			int i = scanner.nextInt();

			if ((currents.size() - 1) < i) {
				System.out.println(WRONG_VALUE);
				start();
			} else {
				MenuElement currentMenuElement = currents.get(i);

				if (currentMenuElement.getAction() != null) {
					currentMenuElement.getAction().execute();
					System.out.println(EXECUTED);
					start();
				} else {
					this.current = currents.get(i);
					start();
				}
			}

		} else {
			String pushed = scanner.next();
			if (pushed.equals("q")) {
				System.out.println("TERMINATED");
				return;
			} else if (pushed.equals("b")) {
				this.current = this.current.getParent();
			}
			start();
		}
	}
}
