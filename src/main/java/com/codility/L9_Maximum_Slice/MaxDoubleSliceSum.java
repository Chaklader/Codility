package com.codility.L9_Maximum_Slice;

/*
* A non-empty array A consisting of N integers is given.

A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called A double slice.

The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

For example, array A such that:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2

contains the following example double slices:

double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A consisting of N integers, returns the maximal sum of any double slice.

For example, given:

    A[0] = 3

    A[1] = 2

    A[2] = 6

    A[3] = -1

    A[4] = 4

    A[5] = 5

    A[6] = -1

    A[7] = 2





the function should return 17, because no double slice of array A has A sum of greater than 17.

Assume that:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−10,000..10,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.util.Arrays;

/**
 * Created by Chaklader on 6/24/18.
 */
public class MaxDoubleSliceSum {


    /*
     * A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a
     * double slice. The sum of double slice (X, Y, Z) is the total
     * of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2]
     * + ... + A[Z − 1]. The goal is to find the maximal sum of any
     * double slice.
     * */


    /*
    * EXPLANATION
    * -----------

    * The key here is that the code does not look for the maximum slice, only for its sum.
    * The array maxStartingHere records at index i what maximum sum you'd reach if you
    * combine contiguous items starting at i+1; maxEndingHere does the same in reverse.
    * Let's look at an example for that:

    i:             0  1  2  3  4
    A:             1 -3  2 -1  ...
    maxEndingHere: 0  1  0  2  1
    Note that:

    i=0: there are no elements left of i, so the sum is 0.
    i=2: Taking A[0..1] is suboptimal, so the maximum of 0 is achieved by not summing anything at all.
    i=4: Another negative element, but 2 + -1 is still better than 0. We're not considering 1 + -3 +
    2 + -1 because we already know that the maximum we can reach left of the 2 is negative.
    I hope you see that this array shows what can be achieved by choosing different X, but the concrete
    choice of X is not recorded - just its consequence. Every i corresponds to a Y, and maxEndingHere[i-1]
    corresponds to the consequence of choosing X optimally for a particular Y.

    So we know what sums choosing X and Z optimally, for a particular Y, result in. That means it only remains
    to choose the best Y (or more precisely: the sum resulting from the best Y). And that is what happens in
    the third loop.

    To reiterate:

        What is the maximum you can get, ending anywhere, when starting from a particular item? That's
        maxStartingHere.

        What is the maximum you can get, starting anywhere, when ending at a particular item? That's
        maxEndingHere.

        What is the maximum you can get when ending/starting at a particular item? That's maxDoubleSlice.
    * */


    // A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
    
    /*
     * solution - a
     * */
    // corerctness: 100%
    public static int solution(int[] A) {


        int N = A.length;

        int[] A1 = new int[N];
        int[] A2 = new int[N];


        /*
          0 ≤ X < Y < Z < N  

          A = {3, 2, 6, -1, 4, 5, -1, 2}
        */

        int max = 0;

        for (int i = 1; i < (N - 2); i++) {        

            max = (A[i] + max) > 0 ? (A[i] + max) : 0;
            A1[i] = max;
        }

        max = 0;

        for (int i = N - 2; i > 1; i--) {

            max = (A[i] + max) > 0 ? (A[i] + max) : 0;
            A2[i] = max;
        }

        max = 0;

        for (int i = 1; i < N - 1; i++) {
            
            max = Math.max(A1[i - 1] + A2[i + 1], max);
        }

        return max;
    }



    // corerctness: 50%
    /*
     * solution - a1
     * */
    public static int solution(int[] A) {


        int max = 0;
        int N = A.length;

        int[] A1 = new int[N];
        int[] A2 = new int[N];

        for (int i = 1; i < N - 3; i++) {

            max = (max + A[i]) > 0 ? (max + A[i]) : 0;
            A1[i] = max;
        }


        max = 0;

        for (int j = N - 2; j >= 3; j--) {

            max = (max + A[j]) > 0 ? (max + A[j]) : 0;
            A2[j] = max;
        }

        int result = 0;

        for (int i = 2; i < (N - 2); i++) {
            result = result > A1[i - 1] + A2[i + 1] ? result : (A1[i - 1] + A2[i + 1]);
        }

        return result;
    }


    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int N = A.length;

        int[] maxStartingHere = new int[N];
        int[] maxEndingHere = new int[N];

        int maxSum = 0;

        /*
            Index =           [0, 1, 2,  3, 4, 5,  6, 7]
            A =               [3, 2, 6, -1, 4, 5, -1, 2]

            maxEndingHere =   [0, 2, 8, 7, 11, 16, 15,0]
            maxStartingHere = [0, 16, 14, 8, 9, 5, 0, 0]
        * */

        /*
         * the maximum slice value between A[0] (excl.)
         * and A[i] (incl.) contains in maxEndingHere[i]
         *
         * prepare for the prefix sum
         * */
        for (int i = 1; i < N - 1; ++i) {

            maxSum = Math.max(0, A[i] + maxSum);
            maxEndingHere[i] = maxSum;
        }


        maxSum = 0;

        /*
         * the maximum slice value between A[N-1] (excl.)
         * and A[i] (incl.) contains in maxStartingHere[i]
         *
         * prepare for the suffix sum
         *
         * A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
         *
         * The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ...
         * + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1]
         *
         * */
        for (int i = N - 2; i > 0; --i) {

            maxSum = Math.max(0, A[i] + maxSum);
            maxStartingHere[i] = maxSum;
        }


        int maxDoubleSlice = 0;

        /*
         * point Y is indicated by the (i+1) index
         * */
        for (int i = 0; i < N - 2; ++i) {
            maxDoubleSlice = Math.max(maxDoubleSlice, maxEndingHere[i] + maxStartingHere[i + 2]);
        }

        return maxDoubleSlice;

    }

    /*
     * solution - c
     */

    /*
     * A1[i - 1] is the maximum sub array on the left of index i
     * and A2[i + 1] is the maximum sub array on the right of
     * index i
     * */
    public static int solution2(int[] A) {

        int max = 0;

        int N = A.length;

        int[] A1 = new int[N];
        int[] A2 = new int[N];

        /*
         * A1[i - 1] is the maximum sub array on the left of index i
         * */
        for (int i = 1; i < N - 1; i++) {
            A1[i] = Math.max(A1[i - 1] + A[i], 0);
        }

        /*
         * A2[i + 1] is the maximum sub array on the right of index i
         * */
        for (int i = N - 2; i >= 1; i--) {
            A2[i] = Math.max(A2[i + 1] + A[i], 0);
        }


        /*
         * A1[i - 1] is the maximum sub array on the left of
         * index i and A2[i + 1] is the maximum sub array on
         * the right of index i
         * */
        for (int i = 1; i < N - 1; i++) {
            max = Math.max(max, A1[i - 1] + A2[i + 1]);
        }

        return max;
    }


    /*
     * solution - d
     */
    public static int solution3(int[] A) {

        int sum = 0;

        int N = A.length;

        int[] lms = new int[N];

        int[] rms = new int[N];


        int minSum = Integer.MAX_VALUE;

        /*
         * the minimum possible value from X (excl.) till the i-th
         * index (inclusive) forward where X is a variable changes
         * with i
         * */
        for (int i = 0; i < N; i++) {

            sum += A[i];

            /*
             * sum will decrease in case of negative values
             * */
            if (sum < minSum) {
                minSum = sum;
            }

            lms[i] = minSum;
        }

        int totalSum = sum;
        sum = 0;

        minSum = Integer.MAX_VALUE;

        /*
         * the minimum possible value from Z (excl.) till the i-th
         * index (inclusive) backward where Z is a variable changes
         * with i
         * */
        for (int i = N - 1; i >= 0; i--) {

            sum += A[i];

            if (sum < minSum) {
                minSum = sum;
            }

            rms[i] = minSum;
        }

        int result = Integer.MIN_VALUE;

        for (int i = 1; i < N - 1; i++) {

            sum = totalSum - A[i] - lms[i - 1] - rms[i + 1];

            if (result < sum) {
                result = sum;
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int[] A = new int[8];

        A[0] = 3;
        A[1] = 2;
        A[2] = 6;
        A[3] = -1;
        A[4] = 4;
        A[5] = 5;
        A[6] = -1;
        A[7] = 2;

        System.out.println(solution(A));
    }
}
