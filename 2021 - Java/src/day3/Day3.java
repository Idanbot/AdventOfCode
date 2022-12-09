package day3;

public class Day3 {

	public static void main(String[] args) {
		Input co2 = new Input();
		Input o2 = new Input();

		int a = o2.parseFile().findO2Rates(o2);
		int b = co2.parseFile().findCO2Rates(co2);

		System.out.println("Life Support rates = " + a * b);

	}

}
