package com.codility.L17_Dynamic_Programming;

/*
* For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:

val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|

(Assume that the sum of zero elements equals zero.)

For a given array A, we are looking for such a sequence S that minimizes val(A,S).

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, computes the minimum value of val(A,S) from all possible values of val(A,S) for all possible sequences S of N integers from the set {−1, 1}.

For example, given array:

  A[0] =  1
  A[1] =  5
  A[2] =  2
  A[3] = -2
your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.

Assume that:

N is an integer within the range [0..20,000];
each element of array A is an integer within the range [−100..100].
Complexity:

expected worst-case time complexity is O(N*max(abs(A))2);
expected worst-case space complexity is O(N+sum(abs(A))) (not counting the storage required for input arguments).
* */

import java.util.Arrays;

/**
 * Created by Chaklader on 7/6/18.
 */
public class MinAbsSum {


    /*
     * Given array of integers, find the lowest absolute sum of elements.
     * For a given array A, we are looking for such a sequence S that
     * minimizes val(A,S) where val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
     * */

    /*
     * solution - a
     * */
    public static int solution(int[] A) {

        int N = A.length;

        if (N == 0) {
            return 0;
        }

        int sum = 0;

        int max = Integer.MIN_VALUE;

        /*
         * change all the value of the array to the
         * absolute values and find max absolute value
         * */
        for (int i = 0; i < N; i++) {

            int value = Math.abs(A[i]);
            sum += value;

            if (max < value) {
                max = value;
            }

            A[i] = value;
        }


        /*
         * O(max(abs(A))) space but no more than O(sum(abs(A))), so ignore it O(N)
         * */
        int[] counts = new int[max + 1];

        for (int value : A) {
            counts[value]++;
        }

        /*
         * O(sum(abs(A)))
         * */
        int[] Total = new int[sum + 1];

        for (int i = 1; i < Total.length; i++) {
            Total[i] = -1;
        }

        /*
         * outer is O(max(abs(A))) inner is O(sum(abs(A))) which is
         * less than O(N * max(abs(A))) we don't care of 0 values
         * */
        for (int i = 1; i < counts.length; i++) {

            /*
             * we check right[j]. if it's not less than 0, then it means we've reached j
             * value with previous steps, so no need to spend current if it's less
             * than 0, spend 1 current number if right[j - i] has been reached
             * */
            for (int j = 0; j < Total.length; j++) {

                /*
                 * negative value means we haven't reached this
                 * value, so we have to spend 1 current if we can
                 * */
                if (Total[j] >= 0) {
                    Total[j] = counts[i];
                } 

                else if (j - i >= 0 && Total[j - i] > 0) {
                    Total[j] = Total[j - i] - 1;
                }

                /*
                 * the value in right[j] then means how many of the current
                 * values are left when we reached the value j
                 * */
            }
        }

        int result = sum;

        /*
         * Don't have to traverse all the arrays, since i - the sum of elements.
         * if it's reachable then (sum - i) - reachable as well. so if the value
         * is reachable then the difference is abs(i - (sum - i)), which is the
         * same as abs(sum - 2 * i)
         *
         * BODMAS RULE = {Brackets, Orders, Division, Multiplication, Addition, Subtraction}
         * */
        for (int i = 0; i < Total.length / 2 + 1; i++) {

            if (Total[i] >= 0 && result > Math.abs(sum - 2 * i)) {
                result = Math.abs(sum - 2 * i);
            }
        }

        return result;
    }


    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int N = A.length;

        int sum = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {

            A[i] = Math.abs(A[i]);
            sum += A[i];

            max = Math.max(A[i], max);
        }

        int[] counts = new int[max + 1];

        for (int i = 0; i < N; i++) {
            counts[A[i]] += 1;
        }




        /*
         * SUBSET SUM PROBLEM
         * ------------------
         *
         * Given a set (or multiset) of integers, is there a non-empty subset whose sum is zero?
         *
         * For example, given the set {−7, −3, −2, 5, 8}, the answer is yes because the subset
         *
         * {−3, −2, 5} sums to zero. The problem is NP-complete, meaning roughly that while it
         *
         * is easy to confirm whether a proposed solution is valid, it may inherently be prohibi
         * tively
         * difficult to determine in the first place whether any solution exists.
         *
         *
         * An equivalent problem: given a set of integers and an integer s, does any non-empty subset
         * sum to s?
         *
         *
         *
         * KNAPSACK PROBLEM
         * ----------------
         * Given a set of items, each with a weight and a value, determine the number of each item
         *
         * included in a collection so that the total weight is less than or equal to a given limit
         *
         * and the total value is as large as possible.
         *
         *
         *
         *
         * PARTITION PROBLEM
         * -----------------
         *
         * It is the task of deciding whether a given multiset S of positive integers can be partitioned
         *
         * into two subsets S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbe
         * rs
         * in S2. Although the partition problem is NP-complete, there is a pseudo-polynomial time dynam
         * ic
         * programming solution, and there are heuristics that solve the problem in many instances, eith
         * er
         * optimally or approximately. For this reason, it has been called "the easiest NP-hard problem"
         * .
         * There is an optimization version of the partition problem, which is to partition the multiset
         * S
         * into two subsets S1, S2 such that the difference between the sum of elements in S1 and the su
         * m
         * of elements in S2 is minimized. The optimization version is NP-hard but can be solved efficie
         * ntly in practice.
         *
         *
         * */

        int[] Total = new int[sum + 1];

        Arrays.fill(Total, -1);
//        Arrays.fill(Total, 1, Total.length, -1);
        Total[0] = 0;


        for (int i = 1; i <= max; i++) {

            if (counts[i] > 0) {

                for (int j = 0; j <= sum; j++) {

                    /*
                     * j th index is zero or positive
                     * */
                    if (Total[j] >= 0) {
                        Total[j] = counts[i];
                    }

                    /*
                     * (i-j) th index is positive
                     * */
                    else if ((j - i) >= 0 && Total[j - i] > 0) {
                        Total[j] = Total[j - i] - 1;
                    }
                }
            }
        }



        int result = sum;

        for (int i = 0; i < (sum/2) + 1; i++) {

            /*
             * i- th index if zero or positive
             * BODMAS  =  {Brackets, Orders, Division, Multiplication, Addition, Subtraction}
             * */

            /*
                BODMAS
                ------

                    Bracket
                    Of
                    Division
                    Multiplication
                    Addition
                    Subtraction
            */
            if (Total[i] >= 0) {
                result = Math.min(result, sum - 2 * i);
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int[] A = {1, 5, 2, -2};
        solution1(A);
    }
}
