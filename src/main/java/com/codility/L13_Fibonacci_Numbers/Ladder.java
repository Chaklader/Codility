package com.codility.L13_Fibonacci_Numbers;

/*
* You have to climb up A ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:

with your first step you can stand on rung 1 or 2,
if you are on rung K, you can move to rungs K + 1 or K + 2,
finally you have to stand on rung N.
Your task is to count the number of different ways of climbing to the top of the ladder.

For example, given N = 4, you have five different ways of climbing, ascending by:

    1, 1, 1 and 1 rung,
    1, 1 and 2 rungs,
    1, 2 and 1 rung,
    2, 1 and 1 rungs, and
    2 and 2 rungs.

Given N = 5, you have eight different ways of climbing, ascending by:

    1, 1, 1, 1 and 1 rung,
    1, 1, 1 and 2 rungs,
    1, 1, 2 and 1 rung,
    1, 2, 1 and 1 rung,
    1, 2 and 2 rungs,
    2, 1, 1 and 1 rungs,
    2, 1 and 2 rungs, and
    2, 2 and 1 rung.

The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for A given integer P.

Write A function:

class Solution { public int[] solution(int[] A, int[] B); }

that, given two non-empty arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; pos I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].

For example, given L = 5 and:

    A[0] = 4   B[0] = 3
    A[1] = 4   B[1] = 2
    A[2] = 5   B[2] = 4
    A[3] = 5   B[3] = 3
    A[4] = 1   B[4] = 1

the function should return the sequence [5, 1, 8, 0, 1], as explained above.

Assume that:

    L is an integer within the range [1..50,000];
    each element of array A is an integer within the range [1..L];
    each element of array B is an integer within the range [1..30].
    Complexity:

expected worst-case time complexity is O(L);
expected worst-case space complexity is O(L) (not counting the storage required for input arguments).
* */

import java.math.BigInteger;

/**
 * Created by Chaklader on 6/25/18.
 */
public class Ladder {



    /*
     * solution - a
     */

    public static int[] solution(int[] A, int[] B) {


        //      * 2^32-1 is the maximum value for a 32-bit unsigned integer
        //      * (32 binary digits). 2^32 is the number of possible values
        //      *
        //      * Confirm that the fibonacci number will not
        //      * exceed the max integer value of 1<<n = 2^n
        //      * 

        //     /*
        //      * SIGNED INTEGER VALUE
        //      * --------------------
        //      *  
        //        Integer.MAX_VALUE =  2 ^ 30
        //      * Integer.MAX_VALUE =  ((1 << 31) - 1)   can be expressed as (1 << 30)
        //      * Integer.MIN_VALUE =  (1 << 31)
        //      * */
        //     int fIndex = i + 2;

        //     fib[fIndex] = (fib[fIndex - 1] + fib[fIndex - 2]) % (1 << 30);
        // }


        int N = A.length;

        int[] F = new int[N + 1];

        F[0] = 1;
        F[1] = 1;


        // 1,1,2,3,5,8,13,21,34,55
        // 0,1,2,3,4,5,6

        // each element of array A is an integer within the range [1..L] where L = A.length
        for (int i = 2; i <= N; i++) {

            F[i] = (F[i - 1] + F[i - 2])%(1<< 30) ;
        }


        int[] result = new int[N];

        for (int i = 0; i < N; i++) {

            result[i] = F[A[i]] % (1 << B[i]);
        }

        return result;
    }


    /*
     * solution - b
     */
    public static int[] solution(int[] A, int[] B) {

        /*
         * we will add 2 more elements in the
         * beginning of fibonacci series
         * */
        BigInteger[] fibs = new BigInteger[A.length + 2];

        fibs[0] = BigInteger.ZERO;
        fibs[1] = BigInteger.ONE;

        /*
         * fibs = [0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597, ....., ...]
         * */
        for (int i = 2; i < A.length + 2; i++) {
            fibs[i] = fibs[i - 1].add(fibs[i - 2]);
        }

        int[] result = new int[A.length];

        for (int i = 0; i < B.length; i++) {

            /*
             * Each element of array A is an integer within the range [1, 2, 3, 4......, N] where N = array size
             * */
            BigInteger currPow = new BigInteger(String.valueOf((long) Math.pow(2, B[i])));
            result[i] = fibs[A[i] + 1].mod(currPow).intValue();
        }

        return result;
    }




    /*
     * solution - c
     */
    public int[] solution2(int[] A, int[] B) {

        int N = A.length;
        int size = N < 2 ? 2 : N;

        int[] fibs = new int[size];

        fibs[0] = 1;
        fibs[1] = 2;

        for (int i = 2; i < A.length; i++) {

            fibs[i] = (fibs[i - 1] + fibs[i - 2]) & ((1 << 30) - 1);
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = fibs[A[i] - 1] & ((1 << B[i]) - 1);
        }

        return A;
    }



    /*
     * solution - d
     * */

    /**
     * Again it's a variant of the classical problem about coins and quantity of change variants.
     * Common recurrence function is:
     * <p>
     * F(n) = sum (j<=Coins.length) ( n - coins[j] )
     * <p>
     * In that case we will sum F(n) = F(n-1) + F(n-2) That is exact fibonacci recurrence relation.
     * So we can use the fibonacci sequence
     */
    public int[] solution4(int A[], int B[]) {

        int[] fibonacciSequence = getFibSequence(B.length + 2);
        int[] result = new int[B.length];

        for (int i = 0; i < B.length; i++) {
            result[i] = fibonacciSequence[A[i]] % (1 << B[i]);
        }

        return result;
    }


    public int[] getFibSequence(int n) {

        int[] result = new int[n + 1];

        result[0] = 0;
        result[1] = 1;
        result[2] = 2;

        for (int i = 3; i <= n; i++) {
            //we don't care about a start of a number
            result[i] = (result[i - 1] + result[i - 2]) % 1073741824;
        }

        return result;
    }
}
