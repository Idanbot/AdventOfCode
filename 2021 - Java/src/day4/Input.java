package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Input {
	private static List<String> data;
	private static List<BingoCard> bingoCards;
	private static List<Integer> chosenNums;

	public static List<BingoCard> getBingoCards() {
		return bingoCards;
	}

	public static List<Integer> getChosenNums() {
		return chosenNums;
	}

	public static void readFromInput() {
		data = new ArrayList<String>();
		bingoCards = new ArrayList<BingoCard>();
		chosenNums = new ArrayList<Integer>();

		try {
			data = Files.readAllLines(
					Paths.get("D:\\Workspace\\eclipse-workspace\\Advent of Code 2021\\src\\day4\\rawData.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int line = 1; line < data.size(); line++) {

			// Ignore empty lines
			if (data.get(line).isEmpty())
				continue;

			// Exporting first line to chosenNums
			String[] items = data.get(0).split(",");
			for (int num = 0; num < items.length; num++) {
				chosenNums.add(Integer.valueOf(items[num].trim()));
			}

			// BingoCard initializer, elm is element from grid
			List<String> rows = new ArrayList<String>();
			BingoCard card;
			for (int elm = 0; elm < BingoCard.GRID_SIZE; elm++) {
				rows.add(data.get(line).trim());
				line++;
			}
			card = new BingoCard(rows);
			bingoCards.add(card);
			
		}
	}
	
	public static int part1(List<BingoCard> cards, List<Integer> numbers) {
		int result = 0;
		for(int num = 0; num < numbers.size(); num++) {
			for(int c = 0; c < cards.size(); c++) {
				BingoCard card = cards.get(c);
				card.checkNum(numbers.get(num));
				if (card.isBingo()) {
					return card.getUnmarkedSum() * numbers.get(num);
				}
			}
		}

		return result;
	}
	
	public static int part2(List<BingoCard> cards, List<Integer> numbers) {
		Map<Integer,Integer> rounds = new HashMap<Integer,Integer>();
		int lastRound = -1;
		for (int num = 0; num < numbers.size(); num++) {
			for (int c = 0; c < cards.size(); c++) {
				BingoCard card = cards.get(c);
				card.checkNum(numbers.get(num));
				if (card.isBingo() && (num>4)) {
					if (lastRound < num) {
						lastRound = num;
					}
					rounds.put(num, card.getUnmarkedSum() * numbers.get(num));
					cards.remove(c--);//Reducing c index by one after removal
				}
			}
		}
		
		return rounds.get(lastRound);
	}

}
