package main

import (
	"bufio"
	"fmt"
	"os"
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

	for _, line := range lines {
		compartments := splitToCompartments(line)
		sharedItem := findSharedItem(compartments)
		sum += runeToPriority(sharedItem)
	}

	return sum
}

func part2(lines []string, length int) int {
	sum := 0
	group := []string{}

	for g := 0; g < length; g += 3 {
		for i := 0; i < 3; i++ {
			group = append(group, lines[g+i])
		}
		sharedItem := findSharedItemInGroup(group)
		sum += runeToPriority(sharedItem)
		group = []string{}
	}

	return sum
}

func runeToPriority(r rune) int {
	if r >= 'a' && r <= 'z' {
		return int(r-'a') + 1
	}

	return int(r-'A') + 27
}

func findSharedItem(compartments []string) rune {
	frequencyMapLeft := make(map[rune]int)
	frequencyMapRight := make(map[rune]int)

	A := compartments[0]
	B := compartments[1]

	for index := range compartments[0] {
		frequencyMapLeft[rune(A[index])]++
		frequencyMapRight[rune(B[index])]++
	}

	for key, value := range frequencyMapLeft {
		if value >= 1 && frequencyMapRight[key] >= 1 {
			return key
		}
	}

	fmt.Println("Error: no common item found")
	return ' '
}

func findSharedItemInGroup(compartments []string) rune {
	frequencyMapFirst := make(map[rune]int)
	frequencyMapSecond := make(map[rune]int)
	frequencyMapThird := make(map[rune]int)

	A := compartments[0]
	B := compartments[1]
	C := compartments[2]

	for index := range A {
		frequencyMapFirst[rune(A[index])]++
	}
	for index := range B {
		frequencyMapSecond[rune(B[index])]++
	}
	for index := range C {
		frequencyMapThird[rune(C[index])]++
	}

	for key, value := range frequencyMapFirst {
		if value >= 1 && frequencyMapSecond[key] >= 1 && frequencyMapThird[key] >= 1 {
			return key
		}
	}

	fmt.Println("Error: no common item found")
	return ' '
}

func splitToCompartments(line string) []string {

	length := len(line)
	middle := length / 2

	if length%2 != 0 {
		fmt.Println("Error: line length is not even")
	}

	compartment := []string{line[:middle], line[middle:]}

	return compartment
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
