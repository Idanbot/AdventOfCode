package day4;

import java.util.List;

public class BingoCard {
	public static final int GRID_SIZE = 5;
	private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
	private boolean[][] found = new boolean[GRID_SIZE][GRID_SIZE];
	private int unmarkedSum = 0;

	public BingoCard(List<String> rows) {
		for (int row = 0; row < rows.size(); row++) {
			String[] items = rows.get(row).split("\s+");
			for (int col = 0; col < items.length; col++) {
				grid[row][col] = Integer.valueOf(items[col].trim());
				unmarkedSum += grid[row][col];
			}
		}
	}

	public void checkNum(int num) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				if (grid[row][col] == num) {
					found[row][col] = true;
					unmarkedSum -= num;
				}
			}
		}
	}
	
	public boolean isBingo() {
		// rows:
		for (int row = 0; row < grid.length; row++) {
			boolean win = true;
			for (int col = 0; col < grid[row].length; col++) {
				if (found[row][col] == false) {
					win = false;
				}
			}
			if (win) {
				return true;
			}
		}

		for (int col = 0; col < grid[0].length; col++) {
			boolean win = true;
			for (int row = 0; row < grid.length; row++) {
				if (found[row][col] == false) {
					win = false;
				}
			}
			if (win) {
				return true;
			}
		}
		return false;
	}
	
	public int getUnmarkedSum() {
		return unmarkedSum;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				str.append(String.format("%3d", grid[row][col]));
			}
			str.append("\n");
		}
		return str.toString();
	}

}
