// package for returning the squares of strictly positive numbers, assuming
// properly formatted inputs.
//
// specification by HDE Japan, sourced externally
// written by Tianhao (Thomas) Wang

package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
	"fmt"
)

func getSquareSum(line []string, runningSum int) {
	// given a line of strings, return the square sum of the nonnegative #'s.
	// this is a recursive solution.

	// recursive end condition
	if len(line) == 0 {
		fmt.Println(runningSum)

		return
	}

	// peek the array and convert to an integer.
	testNumbStr := strings.TrimSpace(line[len(line)-1])
	testNumb, _ := strconv.Atoi(testNumbStr)

	// square and sum it if valid.
	if testNumb > 0 {
		testNumb = testNumb * testNumb
		runningSum += testNumb
	}

	// remove that element.
	line = line[:len(line)-1]

	getSquareSum(line, runningSum)
}

func readGroupings(outputs int, reader bufio.Reader) {
	// recursively perform a number of "read lines", printing the squared sum of
	// all positive integers in the "data line."

	if outputs > 0 {
		// each input has two lines associated. we can throw away the "first" if
		// we don't use for loops.
		_, _ = reader.ReadString('\n')

		// perform formatting magic that makes this all possible.
		numbers, _ := reader.ReadString('\n')
		strArr := strings.Split(numbers, " ")

		// print the result of squaresum.
		getSquareSum(strArr, 0)

		// decrease the iteration counter.
		outputs -= 1

		readGroupings(outputs, reader)
	}
}

func main() {
	// avoiding the use of for, we must instead opt for a recursive iterative
	// solution.

	// read the first line. assume valid inputs.
	reader := bufio.NewReader(os.Stdin)
	istr, _ := reader.ReadString('\n')
	istr = strings.TrimSpace(istr)
	i, _ := strconv.Atoi(istr)

	// perform main program logic.
	readGroupings(i, *reader)
}