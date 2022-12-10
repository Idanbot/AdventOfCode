package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	input := "resources/input.txt"
	lines, length := readFile(input)

	part1 := part1(lines, length)
	part2 := part2(lines, length)

	fmt.Println("Part 1: ", part1)
	fmt.Println("Part 2: ", part2)

}

func part1(lines []string, length int) int {
	sum := 0

	for _, round := range lines {
		sum += roundScore(round)
	}

	return sum
}

func part2(lines []string, length int) int {
	sum := 0

	for _, round := range lines {
		sum += roundResult(round)
	}

	return sum
}

func roundResult(round string) int {
	players := strings.Split(round, " ")

	opponent := []rune(players[0])[0]
	result := []rune(players[1])[0]

	switch result {
	case 'X':
		return counterShape(shapeValue(opponent), false)
	case 'Y':
		return 3 + shapeValue(opponent)
	case 'Z':
		return 6 + counterShape(shapeValue(opponent), true)
	default:
		return 0
	}
}

func roundScore(round string) int {
	players := strings.Split(round, " ")

	opponent := []rune(players[0])[0]
	you := []rune(players[1])[0] - ('X' - 'A')

	if you == opponent {
		return 3 + shapeValue(you)
	}

	if you == opponent+1 {
		return 6 + shapeValue(you)
	}

	if you == opponent-1 {
		return shapeValue(you)
	}

	if you == opponent-2 {
		return 6 + shapeValue(you)
	}

	if you == opponent+2 {
		return shapeValue(you)
	}

	return 0
}

func shapeValue(shape rune) int {
	return int(shape) - 'A' + 1
}

func counterShape(shapeVal int, won bool) int {

	winMap := map[int]int{
		1: 2,
		2: 3,
		3: 1,
	}

	if won {
		return winMap[shapeVal]
	}
	return winMap[winMap[shapeVal]]
}

func readFile(filepath string) ([]string, int) {
	readFile, _ := os.Open(filepath)

	scanner := bufio.NewScanner(readFile)
	scanner.Split(bufio.ScanLines)

	lines := []string{}

	for scanner.Scan() {
		line := scanner.Text()
		lines = append(lines, line)
	}

	length := len(lines)

	readFile.Close()
	return lines, length
}
