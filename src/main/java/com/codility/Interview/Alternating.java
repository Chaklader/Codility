Given an integer n, your task is to calculate the alternating sum of its digits. In other words, add up all the digits, alternating between positive and negative.

For example, if n = 52134, the answer should be 5 - 2 + 1 - 3 + 4 = 5.

Example

For n = 52134, the output should be numberSigningSum(n) = 5.

5 - 2 + 1 - 3 + 4 = 5

For n = 12345, the output should be numberSigningSum(n) = 3.

1 - 2 + 3 - 4 + 5 = 3

For n = 104956, the output should be numberSigningSum(n) = -5.

1 - 0 + 4 - 9 + 5 - 6 = -5

Input/Output

[execution time limit] 3 seconds (java)

[input] integer n

A positive integer number.

Guaranteed constraints:
1 ≤ n ≤ 109.

[output] integer

The alternating sum resulting from the digits of n.
[Java] Syntax Tips

// Prints help message to the console
// Returns a string
// 
// Globals declared here will cause a compilation error,
// declare variables inside the function instead!
String helloWorld(String name) {
    System.out.println("This prints to the console when you Run Tests");
    return "Hello, " + name;
}