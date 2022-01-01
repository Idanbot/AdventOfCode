package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
	private ArrayList<String> binaries;
	private int[] binZeroes;
	private int[] binOnes;
	private int gammaRate;
	private int epsilonRate;
	private int o2Rate;
	private int co2Rate;

	public Input() {
	}

	public Input(int[] binZeroes, int[] binOnes) {
		this.binZeroes = binZeroes;
		this.binOnes = binOnes;
	}

	public int[] getBinZeroes() {
		return binZeroes;
	}

	public void setBinZeroes(int[] binZeroes) {
		this.binZeroes = binZeroes;
	}

	public int[] getBinOnes() {
		return binOnes;
	}

	public void setBinOnes(int[] binOnes) {
		this.binOnes = binOnes;
	}

	public int getGammaRate() {
		return gammaRate;
	}

	public void setGammaRate(int gammaRate) {
		this.gammaRate = gammaRate;
	}

	public int getEpsilonRate() {
		return epsilonRate;
	}

	public void setEpsilonRate(int epsilonRate) {
		this.epsilonRate = epsilonRate;
	}

	public ArrayList<String> getBinaries() {
		return binaries;
	}

	public void setBinaries(ArrayList<String> binaries) {
		this.binaries = binaries;
	}

	public int getO2Rate() {
		return o2Rate;
	}

	public void setO2Rate(int o2Rate) {
		this.o2Rate = o2Rate;
	}

	public int getCo2Rate() {
		return co2Rate;
	}

	public void setCo2Rate(int co2Rate) {
		this.co2Rate = co2Rate;
	}

	@Override
	public String toString() {
		return "binZeroes=" + Arrays.toString(binZeroes) + "\nbinOnes=" + Arrays.toString(binOnes);
	}

	public int checkBinLength() {
		File file = new File("D:\\Workspace\\eclipse-workspace\\Advent of Code 2021\\src\\day3\\rawData.txt");
		try (Scanner reader = new Scanner(file)) {
			if (reader.hasNextLine())
				return reader.nextLine().length();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public Input parseFile() {
		int lengthOfBinaries = checkBinLength();
		ArrayList<String> binariesBuilder = new ArrayList<String>();
		Input result = new Input(new int[lengthOfBinaries], new int[lengthOfBinaries]);
		Scanner reader = null;
		try {
			File file = new File("D:\\Workspace\\eclipse-workspace\\Advent of Code 2021\\src\\day3\\rawData.txt");
			reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				binariesBuilder.add(data);
				for (int i = 0; i < data.length(); i++) {
					if (data.charAt(i) == '0') {
						result.binZeroes[i]++;
					} else {
						if (data.charAt(i) == '1') {
							result.binOnes[i]++;
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			reader.close();
		}
		result.findPowerRates(result);
		// System.out.println("Power consumption = " + result.getEpsilonRate() *
		// result.getGammaRate());
		result.setBinaries(binariesBuilder);
		return result;
	}

	public int findO2Rates(Input input) {
		ArrayList<String> list = new ArrayList<String>(this.getBinaries());
		int bit = 0;
		while (bit < checkBinLength() && list.size() > 1) {
			int zeroes = 0;
			int ones = 0;
			char winner;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).charAt(bit) == '1') {
					ones++;
				} else {
					if (list.get(i).charAt(bit) == '0') {
						zeroes++;
					}
				}
			}
			if (ones >= zeroes) {
				winner = '1';
			} else {
				winner = '0';
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).charAt(bit) != winner) {
					list.remove(i);
					i--;
				}
			}
			bit++;
		}
		System.out.println("O2 Rate - " + Integer.parseInt(list.get(0), 2));
		int res = Integer.parseInt(list.get(0), 2);
		input.setO2Rate(res);
		return res;
	}

	public int findCO2Rates(Input input) {
		ArrayList<String> list = new ArrayList<String>(this.getBinaries());
		int bit = 0;
		while (bit < checkBinLength() && list.size() > 1) {
			int zeroes = 0;
			int ones = 0;
			char winner;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).charAt(bit) == '1') {
					ones++;
				} else {
					if (list.get(i).charAt(bit) == '0') {
						zeroes++;
					}
				}
			}
			if (zeroes <= ones) {
				winner = '0';
			} else {
				winner = '1';
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).charAt(bit) != winner) {
					list.remove(i);
					i--;
				}
			}
			bit++;
		}
		System.out.println("CO2 Rate - " + Integer.parseInt(list.get(0), 2));
		int res = Integer.parseInt(list.get(0), 2);
		input.setCo2Rate(res);
		return res;
	}

	public void findPowerRates(Input input) {
		String gRate = "";
		String eRate = "";

		for (int i = 0; i < binZeroes.length; i++) {
			if (binOnes[i] > binZeroes[i]) {
				gRate += "1";
				eRate += "0";
			} else {
				gRate += "0";
				eRate += "1";
			}
		}
		// System.out.println("Gamma Rate - " + gRate + " \nEpsilon Rate - " + eRate);
		input.setGammaRate(Integer.parseInt(gRate, 2));
		input.setEpsilonRate(Integer.parseInt(eRate, 2));
	}

}
//G 110111001011
//E 001000110111
