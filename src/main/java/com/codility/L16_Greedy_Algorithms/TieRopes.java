package com.codility.L16_Greedy_Algorithms;


/*
* There are N ropes numbered from 0 to N − 1, whose lengths are given in an array A, lying on the floor in A line. For each I (0 ≤ I < N), the length of rope I on the line is A[I].

We say that two ropes I and I + 1 are adjacent. Two adjacent ropes can be tied together with A knot, and the length of the tied rope is the sum of lengths of both ropes. The resulting new rope can then be tied again.

For A given integer K, the goal is to tie the ropes in such A way that the number of ropes whose length is greater than or equal to K is maximal.

For example, consider K = 4 and array A such that:

    A[0] = 1
    A[1] = 2
    A[2] = 3
    A[3] = 4
    A[4] = 1
    A[5] = 1
    A[6] = 3

The ropes are shown in the figure below.



We can tie:

rope 1 with rope 2 to produce A rope of length A[1] + A[2] = 5;
rope 4 with rope 5 with rope 6 to produce A rope of length A[4] + A[5] + A[6] = 5.
After that, there will be three ropes whose lengths are greater than or equal to K = 4. It is not possible to produce four such ropes.

Write A function:

class Solution { public int solution(int K, int[] A); }

that, given an integer K and A non-empty array A of N integers, returns the maximum number of ropes of length greater than or equal to K that can be created.

For example, given K = 4 and array A such that:

    A[0] = 1
    A[1] = 2
    A[2] = 3
    A[3] = 4
    A[4] = 1
    A[5] = 1
    A[6] = 3
the function should return 3, as explained above.

Assume that:

N is an integer within the range [1..100,000];
K is an integer within the range [1..1,000,000,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

/**
 * Created by Chaklader on 6/25/18.
 */
public class TieRopes {


    /*
     * solution - a
     * */
    public static int solution(int[] A, int K) {


        int count = 0;
        int len = 0;

        for (int rope : A) {

            len += rope;

            if (len >= K) {

                count++;
                len = 0;
            }
        }

        return count;
    }
}
