package day2;

public class Day2 {

	public static void main(String[] args) {
		
		Input puzzle = Input.parseFile();
		System.out.println("Depth = " + puzzle.getDepth() + "   Horizontal = " + puzzle.getHorizonal() 
		+ "\nHorizontal * Depth = " + (puzzle.getDepth()*puzzle.getHorizonal()));
	}
}
