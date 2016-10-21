package controllers;

import model.Cook;
import model.Dish;

public class PersonalController {

	private Cook[] cooks;

	public Cook[] getCooks() {
		return cooks;
	}

	public void setCooks(Cook[] cooks) {
		this.cooks = cooks;
	}

	public void addCook(Cook... cooks) {

		if (this.cooks != null) {

			Cook[] newCooksArray = new Cook[this.cooks.length + cooks.length];

			System.arraycopy(this.cooks, 0, newCooksArray, 0, this.cooks.length);
			System.arraycopy(cooks, 0, newCooksArray, this.cooks.length, cooks.length);
			setCooks(newCooksArray);

		} else
			setCooks(cooks);
	}

	public void removeCook(String cookName) {

		try {
			// Checks if elements exist in menu
			int cooksLength = cooks.length;
			Cook[] temp = new Cook[cooksLength - 1];

			for (int i = 0, j = 0; i < cooksLength; i++, j++) {

				if (!cooks[i].getName().equals(cookName)) {

					temp[j] = cooks[i];
				} else
					j--;
			}

			setCooks(temp);

		} catch (NullPointerException e) {
			System.out.println("Something gonna bad: No cooks are works here ....");
		}

	}

	public void viewCooksStatus() {

		StringBuffer sb = new StringBuffer();
		sb.append("COOKS:_____\n");

		for (Cook cook : cooks) {
			sb.append(cook.getName());

			if (cook.getCurrentWork() == null) {
				sb.append(" is free\n");
			} else {
				sb.append(" works with table #").append(cook.getCurrentWork().getTable().getNumber());
				sb.append("; ").append("Dishes: ");
				
				for (Dish cookDish : cook.getCurrentWork().getDishes()) {
					
					sb.append(cookDish.getTitle()).append(", ");
				}
				sb.append("\n");
			}
		}
		sb.append("_____");
		System.out.println(sb);

	}
}
