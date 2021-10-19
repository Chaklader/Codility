package com.codility.L15_Caterpiller_Method;

/*
* An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible to build A triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 12
There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).

Write A function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns the number of triangular triplets in this array.

For example, given array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 12
the function should return 4, as explained above.

Assume that:

N is an integer within the range [0..1,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N2);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Arrays;


/*
* Task Score 100%
* Correctness 100%
* Performance 100%
* /

/**
 * Created by Chaklader on 6/25/18.
 */
public class CountTriangles {



    /*
     * CONDITIONS
     * ----------
     *
     * i.   A[P] + A[Q] > A[R]
     * ii.  A[Q] + A[R] > A[P]
     * iii. A[R] + A[P] > A[Q]
     * */


    /*
     * solution - a
     * */
    public static int solution(int[] A) {


        int N = A.length;
        int result = 0;

        if (N < 3) {
            return 0;
        }

        int front;
        Arrays.sort(A);


        /*
        A[0] = 10    A[1] = 2    A[2] = 5
        A[3] = 1     A[4] = 8    A[5] = 12

        A = [1, 2, 5, 8, 10, 12]        
        */
        for (int i = 0; i < N - 2; i++) {

            front = i + 2;

            for (int j = i + 1; j < N - 1; j++) {

                /*
                 * if A[i] + A[j]) > A[front] satisfies, then the higher
                 * values of j > j(current) will also satisfies the
                 * condition till A[front]
                 * */
                while (front < N && (A[i] + A[j]) > A[front]) {
                    front++;
                }

                /*
                 * (front -1) is the index till the condition is full-filled
                 * */
                result += (front - 1) - j;
            }
        }

        return result;
    }


    /*
     * solution - b
     * */
    public int solution3(int[] A) {

        if (A.length < 3) {
            return 0;
        }

        Arrays.sort(A);
        int result = 0;

        for (int p = 0; p < A.length - 2; p++) {

            int q = p + 1;

            for (int r = p + 2; r < A.length; r++) {

                while (q < r && A[p] + A[q] <= A[r]) {
                    q++;
                }

                result = result + r - q;
            }
        }

        return result;
    }


    /*
     * solution - c
     * */
    public int solution4(int[] A) {

        if (A.length < 3) {
            return 0;
        }

        Arrays.sort(A);
        int result = 0;

        for (int first = 0; first < A.length; first++) {

            int third = first + 1;

            for (int second = first + 1; second < A.length; second++) {

                while (third < A.length && A[first] + A[second] > A[third]) {
                    third++;
                }

                result += third - second - 1;
            }
        }

        return result;
    }

}
