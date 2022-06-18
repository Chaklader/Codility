package com.codility.L4_Counting_Elements;


/*
* You are given N counters, initially set to 0, and you have two possible operations on
* them:

increase(X) − counter X is increased by 1,
getMax counter − all counters are set to the maximum value of any counter.
A non-empty array A of M integers is given. This array represents consecutive operations:

    if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
    if A[K] = N + 1 then operation K is getMax counter.

For example, given integer N = 5 and array A such that:


    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4

the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)

The goal is to calculate the value of every counter after all operations.

Write A function:

    class Solution { public int[] solution(int N, int[] A){}}

that, given an integer N and A non-empty array A consisting of M integers, returns A sequence of integers representing the values of the counters.

The sequence should be returned as:

A structure Results (in C), or
A vector of integers (in C++), or
A record Results (in Pascal), or
an array of integers (in any other programming language).
For example, given:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4

the function should return [3, 2, 2, 4, 2], as explained above.

Assume that:

N and M are integers within the range [1..100,000];
each element of array A is an integer within the range [1..N + 1].
Complexity:

expected worst-case time complexity is O(N+M);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */




/**
 * Created by Chaklader on 6/23/18.
 */
public class MaxCounters {



    /*
     * expected worst-case time complexity is O(N+M) and expected worst-case space complexity is O(N)
     * */

    /*
     * Algorithm
     * ---------
     *
     * i.  if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X)
     * ii. if A[K] = N + 1 then operation K is getMax counter.
     *
     * Assume that:
     *
     * N and M are integers within the range [1..100,000];
     * Each element of array A is an integer within the range [1..N + 1].
     * */


    /*
     * solution - a
     */
    public static int[] solution(int N, int[] A) {

        /*
         * given N counters, initially set to 0
         * */
        int[] result = new int[N];

        int currMax = 0;
        int currMin = 0;

        /**
         *
         *  A[0] = 3
         *  A[1] = 4
         *  A[2] = 4
         *  A[3] = 6
         *  A[4] = 1
         *  A[5] = 4
         *  A[6] = 4
         *
         *  (0, 0, 1, 0, 0)    (0, 0, 1, 0, 0)
         *  (0, 0, 1, 1, 0)    (0, 0, 1, 1, 0)
         *  (0, 0, 1, 2, 0)    (0, 0, 1, 2, 0)
         *  (2, 2, 2, 2, 2)    (0, 0, 1, 2, 0)
         *  (3, 2, 2, 2, 2)    (3, 0, 1, 2, 0)
         *  (3, 2, 2, 3, 2)    (3, 0, 1, 3, 0)
         *  (3, 2, 2, 4, 2)    (3, 0, 1, 4, 0)
         *
         *                     (3, 2, 2, 4, 2)
         *
         */
        
        for (int i = 0; i < A.length; i++) {

            if (1 <= A[i] && A[i] <= N) {

                result[A[i] - 1] = Math.max(currMin, result[A[i] - 1]);
                result[A[i] - 1]++;

                currMax = Math.max(currMax, result[A[i] - 1]);
            }

            /*
             * if A[K] = N + 1 then operation K is getMax counter,
             * use A storage to perform the lazy update to keep O(N)
             * */
            else if (A[i] == N + 1) {
                currMin = currMax;
            }
        }

        /*
         * update the indexes where its required
         * */
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.max(result[i], currMin);
        }

        return result;
    }



    /*
    * solution - a1
    */
    public static int[] solution1(int N, int[] A) {

        int[] result = new int[N];

        int L = A.length;
        int max = Integer.MIN_VALUE;

        boolean bol = false;
        int curr = 0;

        for (int i = 0; i < L; i++) {

            if (1 <= A[i] && A[i] <= N) {

                if (bol) {

                    result[A[i] - 1] = curr + 1;
                    bol = false;
                    continue;
                }

//                result[A[i] -1] = Math.max(result[A[i]-1], curr);
                result[A[i] - 1]++;
                max = Math.max(max, result[A[i] - 1]);
            } 

            else if (A[i] == N + 1) {
                bol = true;
                curr = max;
            }
        }

        for (int i = 0; i < N; i++) {

            if (result[i] < curr) {
                result[i] = curr;
            }
        }

        return result;
    }

    


    /*
     * solution - b
     */
    /*
     * improve the previous method, time complexity is O(N+M).
     * Keep the lastUpdate as the lasgest value in the last round
     * */
    public int[] solution2(int N, int[] A) {


        int[] C = new int[N];

        int currMax = 0;
        int currMin = 0;

        for (int i = 0; i < A.length; i++) {

            /*
             * it's already 1 <= A[i] and hence, no need to check
             * */
            if (1 <= A[i] && A[i] <= N) {

                if (C[A[i] - 1] < currMin) {
                    C[A[i] - 1] = currMin;
                }

                C[A[i] - 1]++;
                currMax = Math.max(currMax, C[A[i] - 1]);
            }

            /*
             * perform for the cindition of A[K] == N + 1
             * */
            else if (A[i] == N + 1) {
                currMin = currMax;
            }
        }

        /*
         * update the indexes where its required
         * */
        for (int i = 0; i < C.length; i++) {
            C[i] = Math.max(C[i], currMin);
        }

        return C;
    }


    /*
     * solution - c
     */
    public int[] solution3(int N, int[] A) {

        int P[] = new int[N];

        for (int i = 0; i < A.length; i++) {

            if (A[i] <= N) {

                P[A[i] - 1]++;
            } 

            else if (A[i] == N + 1) {
                maxCounter(P);
            } 

            else {

            }
        }

        return P;
    }

    /*
     * set max to all the elements of the matrix
     * */
    public void maxCounter(int[] P) {

        int maxNo = getMax(P);

        for (int i = 0; i < P.length; i++) {
            P[i] = maxNo;
        }
    }

    /*
     * get the max value of the matrix
     * */
    public int getMax(int[] P) {

        int max = P[0];

        for (int i = 1; i < P.length; i++) {
            if (P[i] > max) {
                max = P[i];
            }
        }

        return max;
    }

    
}
