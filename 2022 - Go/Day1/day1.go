package day1

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
)

func main() {
	fmt.Println(solution("input.txt"))
}

func solution(input string) int {
	// Open the input file for reading
	file, err := os.Open(input)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	// Create a scanner to read the input file line by line
	scanner := bufio.NewScanner(file)

	// Initialize variables to keep track of the current sum and the largest sum
	var sum, largestSum int

	// Read the input file line by line
	for scanner.Scan() {
		// Get the current line of the input file
		line := scanner.Text()

		// If the current line is empty, update the largest sum if necessary and reset the current sum
		if line == "" {
			if sum > largestSum {
				largestSum = sum
			}
			sum = 0
			continue
		}

		// Convert the current line to a number
		num, err := strconv.Atoi(line)
		if err != nil {
			log.Fatal(err)
		}

		// Add the number to the current sum
		sum += num
	}

	// Print the largest sum
	return largestSum
}
