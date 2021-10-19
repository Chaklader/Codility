

Given an array of integers a, your task is to find the number of pairs of distinct indices (i, j) such that i < j and the sum a[i] + a[j] is equal to a power of two.

Example

For a = [1, 1, -1, 2, 3], the output should be pairSummingToPowerOfTwo(a) = 5.

There is 1 pair of indices where the corresponding elements sum up to 20 = 1:
(2, 3) - a[2] + a[3] = -1 + 2 = 1.
There are 2 pairs of indices where the corresponding elements sum up to 21 = 2:
(0, 1) - a[0] + a[1] = 1 + 1 = 2;
(2, 4) - a[2] + a[4] = -1 + 3 = 2.
There are 2 pairs of indices where the corresponding elements sum up to 22 = 4:
(0, 4) - a[0] + a[4] = 1 + 3 = 4;
(1, 4) - a[1] + a[4] = 1 + 3 = 4.
In total, there are 1 + 2 + 2 = 5 pairs summing up to powers of two.
For a = [2, 2, 2, 2], the output should be pairSummingToPowerOfTwo(a) = 6.

Any pair of elements in the given array sum up to 22 = 4, and there are 6 of them. Note that this only includes pairs of indices of the form (i, j) where i < j.

For a = [-2, -1, 0, 1, 2], the output should be pairSummingToPowerOfTwo(a) = 3.

There are 2 pairs of indices where the corresponding elements sum up to 20 = 1: (2, 3) and (1, 4).
There is 1 pair of indices where the corresponding elements sum up to 21 = 2: (2, 4).
In total, there are 2 + 1 = 3 pairs summing up to powers of two.
Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer a

An array of integers.

Guaranteed constraints:
2 ≤ a.length ≤ 105,
-106 ≤ a[i] ≤ 106.

[output] integer64

The number of pairs of distinct ordered indices, such that the sum of the corresponding elements is equal to some power of two.
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
