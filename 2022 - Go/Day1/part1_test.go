package main

import "testing"

func TestPartOne(t *testing.T) {
	input := "resources/test_input.txt"

	groups, length := readFile(input)

	result := part1(groups, length)
	expected := 24000

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}

func TestPartTwo(t *testing.T) {
	input := "resources/test_input.txt"

	groups, length := readFile(input)

	result := part2(groups, length)
	expected := 45000

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}
