package com.codility.L15_Caterpiller_Method;

/*
* Let A be A non-empty array consisting of N integers.

The abs sum of two for A pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.

For example, the following array A:

      A[0] =  1
      A[1] =  4
      A[2] = -3

    has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
    The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
    The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
    The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
    The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
    The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
    The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.

For example, given the following array A:

  A[0] =  1
  A[1] =  4
  A[2] = -3

the function should return 1, as explained above.

Given array A:

  A[0] = -8
  A[1] =  4
  A[2] =  5
  A[3] =-10
  A[4] =  3

the function should return |(−8) + 5| = 3.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].

Complexity
----------

expected worst-case time complexity is O(N*log(N));

expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Arrays;

public class MinAbsSumOfTwo {


    /*
     * Find the minimal absolute value of a sum of two elements
     *
     * provides 100% test score
     * */
    /*
     * solution - a
     */
    public static int solution(int[] A) {


        Arrays.sort(A);
        int N = A.length;

        /*
         * if the first element is positive, that mean
         * all the elements in the array are positives
         * */
        if (A[0] >= 0) {
            return A[0] * 2;
        }

        /*
         * if the last element is negative, that mean
         * all the elements in the array are negatives
         * */
        if (A[N - 1] <= 0) {
            return -A[N - 1] * 2;
        }

        /*
         * we have mixtures of positive and negative elements
         * */
        int low = 0;
        int high = N - 1;

        int min = Math.abs(A[low] + A[high]);



        while (low <= high) {

            int sum = Math.abs(A[low] + A[high]);

            min = Math.min(min, sum);

            if (Math.abs(A[low + 1] + A[high]) <= sum) {
                low++;
            } 

            else if (Math.abs(A[low] + A[high - 1]) <= sum) {
                high--;
            } 

            else {
                low++;
                high--;
            }
        }

        return min;
    }


    /*
     * solution - b
     */
    public static int solution1(int[] A) {

        int N = A.length;

        Arrays.sort(A);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            min = Math.min(min, Math.abs(A[i] + findBestMatch(-A[i], A)));
        }

        return min;
    }

    public static int findBestMatch(int target, int[] A) {

        int N = A.length;

        if (N == 1) {
            return A[0];
        }

        int low = 0;
        int high = N - 1;

        while (low <= high) {

            int middle = (low + high) / 2;

            if (A[middle] == target) {
                return A[middle];
            }

            if (high - low == 1) {
                return Math.abs(A[high] - target) < Math.abs(A[low] - target) ? A[high] : A[low];
            }

            if (A[middle] > target) {
                high = middle;
            } 

            else {
                low = middle;
            }
        }

        return A[0];
    }


    /*
     * solution - c
     */
    public int solution2(int[] A) {

        int N = A.length;
        Arrays.sort(A);

        int low = 0;
        int high = N - 1;

        int min = Integer.MAX_VALUE;

        while (low <= high) {

            int sum = A[low] + A[high];
            min = Math.min(min, Math.abs(sum));

            if (sum <= 0) {
                low++;
            } 

            else {
                high--;
            }
        }

        return min;
    }


    /*
     * solution - d
     */
    public int solution3(int[] A) {

        int N = A.length;

        Arrays.sort(A);
        int j = N - 1;

        int result = Integer.MAX_VALUE;

        for (int value : A) {

            int best = Math.abs(value + A[j]);

            /*
             * beast min abs value for A is -A with j we move from the end to the
             * beginning of the array if abs starts growing, it will never be less
             * again so we find the best value for the i-th element A[i] <= A[i + 1],
             * so A[i + 1]-th best summand is to the left from A[i]-th it cannot be
             * to the right of j-th position (just draw A plot with key axis to see i)
             * */
            while (j > 0 && Math.abs(value + A[j - 1]) <= best) {
                j--;
                best = Math.abs(value + A[j]);
            }

            if (result > best) {
                result = best;
            }
        }

        return result;
    }


    /*
     * solution - e
     */
    public int solution4(int[] A) {

        int N = A.length;
        Arrays.sort(A);

        int front = N - 1;
        int back = 0;
        int min = Integer.MAX_VALUE;

        while (back <= front) {

            int current = A[back] + A[front];
            min = Math.min(min, Math.abs(current));

            if (current <= 0) {
                back++;
            } 

            else {
                front--;
            }

        }

        return min;
    }


    /*
     * solution - f
     * */
    public int solution5(int[] A) {

        int N = A.length;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {

            for (int j = i; j < N; j++) {
                min = Math.min(min, Math.abs(A[i] + A[j]));
            }
        }

        return min;
    }
}
