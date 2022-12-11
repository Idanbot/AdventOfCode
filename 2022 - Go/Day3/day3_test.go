package main

import (
	"testing"
)

func loadPart1() ([]string, int) {
	input := "resources/test_input_1.txt"

	return readFile(input)
}

func loadPart2() ([]string, int) {
	input := "resources/test_input_2.txt"

	return readFile(input)
}

func TestPartOne(t *testing.T) {
	result := part1(loadPart1())
	expected := 157

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}

func TestPartTwo(t *testing.T) {
	result := part2(loadPart2())
	expected := 70

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}
