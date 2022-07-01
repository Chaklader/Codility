package com.codility.L5_Prefix_Sums;

/*
* Write A function:

class Solution { public int solution(int A, int B, int K); }

that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:

{ i : A ≤ i ≤ B, i mod K = 0 }

For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.

Assume that:

A and B are integers within the range [0..2,000,000,000];
K is an integer within the range [1..2,000,000,000];
A ≤ B.
Complexity:

expected worst-case time complexity is O(1);
expected worst-case space complexity is O(1).
* */

/**
 * Created by Chaklader on 6/23/18.
 */
public class CountDiv {


    /*
     * f(i) = { i : A ≤ i ≤ B, i % K = 0 }
     *
     *
     * CONDITIONS
     * ----------
     *
     * i.   A and B are integers within the range [0..2,000,000,000]
     *
     * ii.  K is an integer within the range [1..2,000,000,000]
     *
     * iii. A ≤ B.
     *
     * */


    /*
     * solution - a
     */
    public static int solution(int A, int B, int K) {


        int result = (B - A) / K;

        if (A % K == 0 || B % K == 0) {
            return result++;
        }

        return result;
    }


    /*
     * solution - c
     */
    public static int solution2(int A, int B, int K) {

        int b = B / K;

        int a = A > 0 ? (A - 1) / K : 0;

        /*
         * if A > 0,
         *
         * result is (B - A + 1)/K
         * */
        if (A == 0) {
            b++;
        }

        return b - a;
    }


    public static void main(String[] args) {

        solution2(6, 11, 2);
    }
}
