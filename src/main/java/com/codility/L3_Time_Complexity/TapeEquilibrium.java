package com.codility.L3_Time_Complexity;

/*

A non-empty array A consisting of N integers is given. Array A represents numbers on A tape.

Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

In other words, it is the absolute difference between the sum of the first part and the sum of the second part.

For example, consider array A such that:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 4
    A[4] = 3

We can split this tape in four places:

    P = 1, difference = |3 − 10| = 7
    P = 2, difference = |4 − 9| = 5
    P = 3, difference = |6 − 7| = 1
    P = 4, difference = |10 − 3| = 7

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A of N integers, returns the minimal difference that can be achieved.

For example, given:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 4
    A[4] = 3

The function should return 1, as explained above.


Assume that:


    N is an integer within the range [2..100,000];
    each element of array A is an integer within the range [−1,000..1,000].
    Complexity:

    expected worst-case time complexity is O(N);
    expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */




/**
 * Created by Chaklader on 6/23/18.
 */

/*
 * Given A non-empty array A of N integers, returns the minimal difference that can be achieved.
 * */
public class TapeEquilibrium {




    /*
     * solution - a
     * */
    public static int solution(int[] A) {


        int result = Integer.MAX_VALUE;
//         int result = (1 << 30);

        int tmp = 0;
        int sum = 0;

        // for (int i = 0; i < A.length; i++) {
        //     sum += A[i];
        // }

        for (int a : A) {
            sum += a;
        }

        for (int i = 0; i < A.length - 1; i++) {

            tmp += A[i];

            int current = Math.abs(tmp - (sum - tmp));
            result = Math.min(result, current);
        }

        return result;
    }


    /*
     * solution - b
     * */
    public int solution1(int[] A) {

        int N = A.length;

        if (N == 1) {
            return A[0];
        }

        int sum = 0;
        int left = 0;

        for (int i = 0; i < N; i++) {
            sum += A[i];
        }

        int result = Math.abs(A[0] - (sum - A[0]));

        for (int i = 1; i < A.length; i++) {

            for (int j = 0; j < i; j++) {
                left += A[j];
            }

            result = less(result, Math.abs(left - (sum - left)));
            left = 0;
        }

        return result;
    }


    /*
     * get the lesser value between the provided two values
     * */
    public int less(int a, int b) {
        if (a <= b) {
            return a;
        } 

        return b;
    }


    /*
     * solution - c
     * */
    public int solution3(int[] A) {

        int sum = 0;
        int result = Integer.MAX_VALUE;

        for (final int value : A) {
            sum += value;
        }

        int left = 0;
        int right = sum;

        for (int i = 1; i < A.length; i++) {

            left += A[i - 1];
            right -= A[i - 1];

            int value = Math.abs(right - left);
            result = result > value? result: value;
        }

        return result;
    }


    /*
     * solution - d
     * */
    public int solution4(int[] A) {

        int[] C = new int[A.length];
        C[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            C[i] = A[i] + C[i - 1];
        }

        int[] D = new int[A.length];

        D[D.length - 1] = A[D.length - 1];

        for (int i = A.length - 2; i >= 0; i--) {
            D[i] = D[i + 1] + A[i];
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i < A.length; i++) {
            min = Math.min(min, Math.abs(C[i - 1] - D[i]));
        }

        return min;
    }


}
