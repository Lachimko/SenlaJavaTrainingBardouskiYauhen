package classes.controllers;

public class DigitSummaryCalcilator {

	/**
	 * algorithm:
	 * (123 % 1000) / 100) = 1 X X;
	 * (123 % 100) / 10) = X 2 X;
	 * (123 % 10) / 1) = X X 3
	 * */
	public static void calculateSummOfDigits(int number) {

		int digitCount = Integer.toString(number).length();
		int divider = 1, summ = 0;

		for (int i = 0; i < digitCount; i++) {

			summ += (int)((number % (divider * 10)) / divider);
			divider *= 10;
		}
		
		System.out.printf("Summ of %d digits is: %d", number, summ);

	}
}
