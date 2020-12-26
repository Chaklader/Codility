package com.codility.L15_Caterpiller_Method;

/*
* An integer M and A non-empty array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.

A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called A slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is A slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.

For example, consider integer M = 6 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 5
    A[3] = 5
    A[4] = 2
There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).

The goal is to calculate the number of distinct slices.

Write A function:

class Solution { public int solution(int M, int[] A); }

that, given an integer M and A non-empty array A consisting of N integers, returns the number of distinct slices.

If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.

For example, given integer M = 6 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 5
    A[3] = 5
    A[4] = 2
the function should return 9, as explained above.

Assume that:

N is an integer within the range [1..100,000];
M is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..M].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(M) (not counting the storage required for input arguments).
* */


/**
 * Created by Chaklader on 7/5/18.
 */
public class CountDistinctSlices {


    public static int solution(int[] A) {

        int N = A.length;

        Arrays.sort(A);
        int result = 0;

        for (int i = 0; i < N - 2; i++) {

            int k = i + 2;

            for (int j = i + 1; j < N - 1; j++) {

                int count = 0;

                k = j + 1;

                while (k < N && A[i] + A[j] > A[k]) {

                    count++;
                    k++;
                }


                result += count;
            }
        }

        // If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
        if(result > 1000000000){
            
            return 1000000000;
        }


        return result;
    }

    

    /*
     * solution - a
     * */
    public static int solution1(int M, int[] A) {

        // each element of array A is an integer within the range [0..M]
        boolean[] visited = new boolean[M + 1];

        int N = A.length;
        int i = 0;
        int result = 0;

        while (i < N) {

            /*
                A[0] = 3
                A[1] = 4
                A[2] = 5
                A[3] = 5
                A[4] = 2
            */

            int count = 0;
            while (i < N && !visited[A[i]]) {

                visited[A[i]] = true;
                count++;

                i++;
            }

            int j = 0;

            while (j < i) {
                visited[A[j]] = false;
                j++;
            }

            result += count * (count + 1) / 2;
        }

        return Math.min(result, 1000000000);
    }



    /*
     * The goal is to calculate the number of distinct slices
     * */
    /*
     * solution - b
     * */
    public static int solution(int[] A, int M) {

        int result = 0;

        int back = 0;
        int front = 0;

        int N = A.length;

        /*
         * All integers in array A are less than or equal to M
         * */
        boolean[] visited = new boolean[M + 1];


        /*
         * int[] A = {3, 4, 5, 5, 2};
         * N = 5
         * */
        while (front < N && back < N) {


            /*
             * Move forward till we find A member already visited. This
             * loop will start from the same value of front and back
             * */
            while (front < N && !visited[A[front]]) {

                /*
                 * for the n-th element of the array, we add n additional
                 * entries
                 * */
                result += front - back + 1;

                /*
                 * set the value as visited
                 * */
                visited[A[front]] = true;
                front++;
            }

            /*
             * break when we get the same element
             *
             * A = [3, 4, 5, 5, 2]
             * */
            while (front < N && back < N && A[back] != A[front]) {
                visited[A[back]] = false;
                back++;
            }

            visited[A[back]] = false;
            back++;
        }
		
        return Math.min(result, 1000000000);
    }



    /*
     * solution - c
     * */
    public static int solution2(int M, int[] A) {


        /*
         * The algorithm is that for each back the front points at the
         * most right place where there is no same element between back
         * and front. We know that the worst case is that all the elements
         * of the array are the same, and at this circumstance the time
         * complexity is O(2*N).
         * */
        int N = A.length;

        int[] counters = new int[M + 1];

        int front = 0;
        int back = 0;

        int result = 0;

        /*
         * ALGORITHM
         * ---------
         *
         * i.   increase the front till find a duplicate where front < N
         *
         * ii.  calculate the result as the subtraction of front and back
         *
         * iii. increase the back and set 0 in the counter where back < N.
         *      when the back will set 0 for the dups, the front can move
         *      froward again
         * */

        while (back < N) {

            /*
             * find the most right end of the array for each back
             *
             * A = [5, 3, 4, 5, 2]
             * */
            while (front < N && counters[A[front]] != 2) {

                counters[A[front]]++;

                if (counters[A[front]] == 2) {
                    break;
                }

                front++;
            }

            result += front - back;

            if (result > 1000000000) {
                return 1000000000;
            }

            /*
             * set the counter of the element before back to be 0
             * */
            counters[A[back]] = 0;
            back++;
        }

        return result;
    }


    /*
     * solution - d
     * */
    public int solution3(int M, int[] A) {

        boolean[] D = new boolean[M + 1];

        int i = 0;
        int j = 0;
        int k = -1;

        int result = 0;


        /*
         * total O(N) since i and j are only increasing
         * */
        while (j < A.length) {

            int c = 0;
            int n;

            /*
             * there will be repeated slices. remove them
             * */
            if (k >= 0) {

                n = k - i;
                c = c - (n * (n + 1) / 2);
            }

            while (j < A.length && !D[A[j]]) {
                D[A[j]] = true;
                j++;
            }

            k = j;
            n = j - i;


            /*
             * count all slices for A fragment. repeated already removed
             * */

            c = c + (n * (n + 1) / 2);
            result += c;

            /*
             * no more than already passed j steps, and no
             * more A.length in total during the solution run
             * */
            while (i < j) {

                D[A[i]] = false;
                i++;

                if (k != -1 && k < A.length && A[i - 1] == A[k]) {
                    break;
                }
            }

            if (result > 1000000000) {
                result = 1000000000;
                break;
            }
        }

        return (int) result;
    }


    /*
     * solution - e
     * */
    public int solution4(int M, int[] A) {

        boolean[] found = new boolean[M + 1];

        int count = 0;
        int back = 0;

        for (int front = 0; front < A.length; front++) {

            while (found[A[front]]) {

                found[A[back]] = false;
                back++;
            }

            found[A[front]] = true;
            count += front - back + 1;

            if (count > 1000000000) {
                return 1000000000;
            }
        }

        return count;
    }


    public static void main(String[] args) {

//        int[] A = new int[5];

//        A[0] = 3;
//        A[1] = 4;
//        A[2] = 5;
//        A[3] = 5;
//        A[4] = 2;

//        int[] A = {3, 4, 5, 5, 2};
//        int[] A = {5, 3, 4, 5, 2};
        int[] A = {3, 5, 4, 5, 2};

        int M = 6;

        System.out.println(solution1(M, A));
    }
}
