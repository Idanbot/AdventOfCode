package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

	private int horizonal;
	private int depth;
	private int aim;

	public Input(int horizontal, int depth, int aim) {
		this.horizonal = horizontal;
		this.depth = depth;
		this.aim = aim;
	}

	public int getAim() {
		return aim;
	}

	public void setAim(int aim) {
		this.aim = aim;
	}

	public int getHorizonal() {
		return horizonal;
	}

	public void setHorizonal(int horizonal) {
		this.horizonal = horizonal;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public static Input parseFile() {
		Input result = new Input(0, 0, 0);
		int amount;
		String[] step = new String[2];
		try {
			File file = new File("D:\\Workspace\\eclipse-workspace\\Advent of Code 2021\\src\\day2\\rawData.txt");
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				step = data.split(" ");
				amount = Integer.parseInt(step[1]);
				switch (step[0]) {
				case "forward":
					result.setHorizonal(result.getHorizonal() + amount); // From Part1
					result.setDepth(result.getDepth() + result.getAim() * amount);
					break;
				case "down":
					// result.setDepth(result.getDepth() + amount); //From Part1
					result.setAim(result.getAim() + amount);
					break;
				case "up":
					// result.setDepth(result.getDepth() - amount); //From Part1
					result.setAim(result.getAim() - amount);
					break;
				default:
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return result;
	}

}
