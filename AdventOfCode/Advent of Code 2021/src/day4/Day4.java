package day4;

public class Day4 {

	public static void main(String[] args) {
		Input.readFromInput();
		System.out.println("Part 1: " + Input.part1(Input.getBingoCards(), Input.getChosenNums()));
		Input.readFromInput();
		System.out.println("Part 2: " + Input.part2(Input.getBingoCards(), Input.getChosenNums()));
	}
}
