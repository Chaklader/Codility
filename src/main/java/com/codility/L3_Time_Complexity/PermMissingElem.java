package com.codility.L3_Time_Complexity;

/*
* 

    An array A consisting of N different integers is given. The array contains 
    integers in the range [1..(N + 1)], which means that exactly one element is 
    missing.


Your goal is to find that missing element.


Write A function:

    class Solution { public int solution(int[] A); }

that, given an array A, returns the value of the missing element.

For example, given array A such that:

      A[0] = 2
      A[1] = 3
      A[2] = 1
      A[3] = 5

the function should return 4, as it is the missing element.

Assume that:

N is an integer within the range [0..100,000];
the elements of A are all distinct;
each element of array A is an integer within the range [1..(N + 1)].
Complexity:

    expected worst-case time complexity is O(N);
    expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */


import java.math.BigInteger;

/**
 * Created by Chaklader on 6/23/18.
 */
public class PermMissingElem {


    /*
     * solution - e
     */
    public int solution5(int[] A) {

        int N = A.length;

        int[] C = new int[N + 2];
        C[0] = -1;

        for (int i = 0; i < N; i++) {
            C[A[i]] = -1;
        }

        for (int i = 1; i < (N + 2); i++) {

            if (C[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    /*
     * solution - a
     */
    public static int solution(int[] A) {

        int N = A.length;

        if(N == 0){
            return 1;
        }

        /*
            A[0] = 2
            A[1] = 3
            A[2] = 1
            A[3] = 5
        */
        for (int i = 0; i < N; i++) {

            if (A[i] == 0) {
                continue;
            }

            int j = A[i] - 1;

            while (j >= 0 && j < N) {

                int k = A[j] - 1;

                A[j] = 0;
                j = k;
            }
        }

        for (int i = 0; i < A.length; i++) {

            if (A[i] != 0) {
                return i + 1;
            }
        }

        return (N + 1);
    }


    /*
     * solution - b
     * */
    public int solution1(int[] A) {

        int N = A.length;

        int sum = ((N + 1) * (N + 2)) / 2;

        for (int a : A) {
            sum -= a;
        }

        return sum;
    }

    public static void main(String[] args) {

        int[] A = {2, 3, 1, 5};
        System.out.println(solution(A));
    }
}
