package day1;
import java.util.ArrayList;

public class Day1 {

	public static void main(String[] args) {
		System.out.println(swipe());
		System.out.println(swipeOfThreeSums());
	}

	//Answer = 1475
	public static int swipe() {
		int counter = 0;
		ArrayList<Integer> array = Input.getIntInput();
		int temp = array.get(0);
		for (int i = 1; i < array.size(); i++) {
			if(array.get(i) > temp) {
				counter++;
			}
			temp = array.get(i);
		}
		return counter;
	}
	
	//Answer = 1516
	public static int swipeOfThreeSums() {
		int counter = 0;
		ArrayList<Integer> array = Input.getIntInput();
		int tempSum = array.get(0) + array.get(1) + array.get(2);
		int currentSum = 0;
		for (int i = 1; i < array.size()-2; i++) {
			currentSum = array.get(i) + array.get(i+1) + array.get(i+2);
			if(currentSum > tempSum) {
				counter++;
			}
			tempSum = currentSum;
		}
		return counter;
	}
}
