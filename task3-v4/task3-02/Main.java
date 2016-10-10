import classes.controllers.ThreeRankNumberGenerator;
import static classes.controllers.DigitSummaryCalcilator.*;

public class Main {

	public static void main(String[] args) {

		ThreeRankNumberGenerator numberGenerator = new ThreeRankNumberGenerator();
		final int DIGIT_QUANTITY = 3;
		
		int generatedNumber = numberGenerator.generateRandom(DIGIT_QUANTITY);
		System.out.println("Generated ramdom: " + generatedNumber);
		
		calculateSummOfDigits(generatedNumber);

		

	}
}
