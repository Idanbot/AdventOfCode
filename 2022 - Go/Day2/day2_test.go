package main

import "testing"

func loadFile() ([]string, int) {
	input := "resources/test_input.txt"

	return readFile(input)
}

func TestPartOne(t *testing.T) {
	result := part1(loadFile())
	expected := 15

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}

func TestPartTwo(t *testing.T) {
	result := part2(loadFile())
	expected := 12

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}
