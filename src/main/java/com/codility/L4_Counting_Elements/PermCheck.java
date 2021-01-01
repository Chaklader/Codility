package com.codility.L4_Counting_Elements;

/*
* 
A non-empty array A consisting of N integers is given.

A permutation is A sequence containing each element from 1 to N once, and only once.

For example, array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2

Is a permutation, but array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3

is not A permutation, because value 2 is missing.

The goal is to check whether array A is A permutation.

Write A function:

class Solution { public int solution(int[] A); }

that, given an array A, returns 1 if array A is A permutation and 0 if it is not.

For example, given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
the function should return 1.

Given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
the function should return 0.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Arrays;

/**
 * Created by Chaklader on 6/23/18.
 */
public class PermCheck {



    /*
     * Expected worst-case time complexity is O(N) and
     * expected worst-case space complexity is O(N)
     * */


    /*
     * solution - a
     */
    public static int solution(int[] A) {


        int N = A.length;

        for (int i = 0; i < A.length; i++) {

            if (A[i] > N) {
                return 0;
            }

            if (A[i] == 0) {
                continue;
            }

            int j = A[i] - 1;

            while (A[j] > 0 && j < N) {

                int k = A[j] - 1;
                A[j] = 0;

                j = k;
            }                        
        }

        for (int i = 0; i < A.length; i++) {

            if (A[i] != 0) {
                return 0;
            }
        }

        return 1;
    }



    /*
     * solution - b
     */
    public static int solution1(int[] A) {

        Arrays.sort(A);

        int N = A.length;

        for (int i = 0; i < N; i++) {

            if(A[i] > N){
                return 0;
            }

            int j = A[i] -1;

            if (i != j) {
                return 0;
            }
        }

        return 1;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        int N = A.length;
        boolean[] check = new boolean[N];

        for (int i = 0; i < N; i++) {

            // this tackles the bigger number 
            if(A[i] > N){
                return 0;
            }

            int j = A[i] - 1;

            // this tackle the duplicate number 
            if (check[j]) {
                return 0;
            }

            check[j] = true;
        }

        return 1;
    }


    /*
     * solution - d
     */
    public int solution3(int[] A) {

        int[] C = new int[A.length];
        Arrays.fill(C, -1);

        for (int i = 0; i < A.length; i++) {

            if (A[i] > A.length) {
                return 0;
            }

            int j = A[i] - 1;

            if (C[j] == -1) {
                C[j] = A[i];
            }
        }

        for (int i = 0; i < C.length; i++) {

            if (C[i] == -1) {
                return 0;
            }
        }

        return 1;
    }


    public static void main(String[] args) {

        int[] A = {4, 1, 3, 2};
        solution(A);
    }
}
