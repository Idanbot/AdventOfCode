package day1

import "testing"

func TestPartOne(t *testing.T) {
	result := solution("test_input.txt")
	expected := 24000

	if result != expected {
		t.Errorf("expected '%d' but got '%d'", expected, result)
	}
}
