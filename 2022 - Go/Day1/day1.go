package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
)

func main() {
	input := "resources/input.txt"

	groups, length := readFile(input)

	part1 := part1(groups, length)
	part2 := part2(groups, length)

	fmt.Println("Part 1: ", part1)
	fmt.Println("Part 2: ", part2)
}

func part1(groups []int, length int) int {
	sort.Ints(groups)

	return groups[length-1]
}

func part2(groups []int, length int) int {
	sum := 0

	for _, calories := range groups[length-3:] {
		sum += calories
	}

	return sum
}

func readFile(filepath string) ([]int, int) {
	readFile, _ := os.Open(filepath)

	scanner := bufio.NewScanner(readFile)
	scanner.Split(bufio.ScanLines)

	groups := []int{0}

	for scanner.Scan() {
		line := scanner.Text()

		if line == "" {
			groups = append(groups, 0)
			continue
		}

		num, _ := strconv.Atoi(line)
		groups[len(groups)-1] += num
	}

	length := len(groups)

	readFile.Close()
	return groups, length
}
