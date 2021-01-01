package com.codility.L6_Sorting;

/*
* An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

    A[P] + A[Q] > A[R],
    A[Q] + A[R] > A[P],
    A[R] + A[P] > A[Q].

For example, consider array A such that:

    A[0] = 10    A[1] = 2    A[2] = 5
    A[3] = 1     A[4] = 8    A[5] = 20

Triplet (0, 2, 4) is triangular.

Write A function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns 1 if there exists A triangular triplet for this array and returns 0 otherwise.

For example, given array A such that:

    A[0] = 10    A[1] = 2    A[2] = 5
    A[3] = 1     A[4] = 8    A[5] = 20

the function should return 1, as explained above. Given array A such that:

    A[0] = 10    A[1] = 50    A[2] = 5
    A[3] = 1

the function should return 0.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:


    expected worst-case time complexity is O(N*log(N));

    expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Arrays;

/**
 * Created by Chaklader on 6/24/18.
 */
public class Triangle {


    /*
     * Given an array A consisting of N integers, returns 1 if
     * there exists A triangular triplet for this array and
     * returns 0 otherwise.
     * */

    /*
     * solution - a
     */
    public static int solution(int[] A) {

        int N = A.length;

        if (N < 3) {
            return 0;
        }

        Arrays.sort(A);

        for (int i = 2; i < N; i++) {

            if (A[i - 2] + A[i - 1] > A[i]) {
                return 1;
            }
        }

        return 0;
    }
    
}
