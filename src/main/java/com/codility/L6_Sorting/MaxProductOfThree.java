package com.codility.L6_Sorting;

/*
* A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Assume that:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */


import java.util.Arrays;

/**
 * Created by Chaklader on 6/24/18.
 */
public class MaxProductOfThree {


    /*
     * solution - a
     */
    public static int solution(int[] A) {


        Arrays.sort(A);
        int N = A.length;

        int all = A[N - 1] * A[N - 2] * A[N - 3];
        int mixtures = A[N - 1] * A[0] * A[1];

        return Math.max(all, mixtures);
    }


    /*
     * solution - b
     */
    public int solution1(int[] A) {

        int N = A.length;

        /*
         * Invariant: maxes[0] <= maxes[1] <= maxes[2]
         * */
        int[] maxes = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        /*
         * Invariant: mins[0] <= mins[1]
         * */
        int[] mins = {Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int a : A) {
                  
            updateMaxes(a, maxes);
            updateMins(a, mins);
        }

        int obvious = maxes[0] * maxes[1] * maxes[2];
        int twoBigNegs = mins[0] * mins[1] * maxes[2];

        return Math.max(obvious, twoBigNegs);
    }

    private static void updateMaxes(int a, int[] maxes) {

        if (a >= maxes[2]) {

            maxes[0] = maxes[1];
            maxes[1] = maxes[2];
            maxes[2] = a;
        } 

        // 
        else if (a >= maxes[1]) {

            maxes[0] = maxes[1];
            maxes[1] = a;
        } 

        // 
        else if (a > maxes[0]) {

            maxes[0] = a;
        }
    }

    private static void updateMins(int a, int[] mins) {

        if (a <= mins[0]) {
            mins[0] = a;
            mins[1] = mins[0];
        } 

        else if (a < mins[1]) {
            mins[1] = a;
        }
    }


    /*
     * solution - c
     */

    /*
     * O(n) solution. O(n * log(n)) involves sorting, than calculation two
     * possible maximums. First, second, last (in case first and second are
     * negative and give product more than last - 2 and last - 1) and last-2,
     * last-1, last
     * */
    public int solution2(int[] A) {


        int minOne = A[0] > A[1] ? A[1] : A[0];
        int maxOne = A[0] > A[1] ? A[0] : A[1];

        int minTwo = A[0] * A[1];
        int maxTwo = A[0] * A[1];

        int result = Integer.MIN_VALUE;

        for (int i = 2; i < A.length; i++) {

            /*
             * find max triplet value
             * */
            int tmp = minTwo * A[i];

            if (tmp > result) {
                result = tmp;
            }

            tmp = maxTwo * A[i];

            if (tmp > result) {
                result = tmp;
            }

            /*
             * find max/min duplets values
             * */
            tmp = minOne * A[i];

            if (tmp < minTwo) {
                minTwo = tmp;
            }

            if (tmp > maxTwo) {
                maxTwo = tmp;
            }

            tmp = maxOne * A[i];

            if (tmp < minTwo) {
                minTwo = tmp;
            }

            if (tmp > maxTwo) {
                maxTwo = tmp;
            }

            /*
             * find max/min values;
             * */
            if (A[i] < minOne) {
                minOne = A[i];
            }

            if (A[i] > maxOne) {
                maxOne = A[i];
            }
        }

        return result;
    }
}
